package com.cagataysunal.restaurantordertracker.data.websocket

import com.cagataysunal.restaurantordertracker.domain.repository.TokenProvider
import com.pusher.client.ChannelAuthorizer
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import timber.log.Timber

class CustomAuthorizer(
    private val client: HttpClient,
    private val tokenProvider: TokenProvider,
) : ChannelAuthorizer {
    override fun authorize(channelName: String, socketId: String): String {
        return runBlocking {
            try {
                val token = tokenProvider.getToken()
                val response: HttpResponse =
                    client.post("http://188.34.155.223/new-qr-menu/api/broadcasting/auth") {
                        contentType(ContentType.Application.Json)
                        header("Authorization", "Bearer $token")
                        header("Accept", "application/json")
                        header("X-Requested-With", "XMLHttpRequest")
                        setBody(mapOf("socket_id" to socketId, "channel_name" to channelName))
                    }
                val responseBody = response.bodyAsText()
                // Assuming the response is a JSON with a field "auth"
                JSONObject(responseBody).getString("auth")
            } catch (e: Exception) {
                Timber.e(e, "Pusher authentication failed")
                throw e
            }
        }
    }
}

class PusherManager(
    private val tokenProvider: TokenProvider,
    private val httpClient: HttpClient
) {
    private lateinit var pusher: Pusher

    fun init(restaurantId: String) {
        val authorizer = CustomAuthorizer(httpClient, tokenProvider)

        val options = PusherOptions().apply {
            setHost("188.34.155.223")
            setWsPort(6001)
            setUseTLS(false)
            channelAuthorizer = authorizer
        }

        pusher = Pusher("vun3o0gckcobpaxwpi3u", options)

        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Timber.d("Pusher connection state changed to: ${change.currentState}")
                if (change.currentState == ConnectionState.CONNECTED) {
                    subscribeToChannel(restaurantId)
                }
            }

            override fun onError(message: String?, code: String?, e: Exception?) {
                Timber.e(e, "Pusher connection error: $message (code: $code)")
            }
        })
    }

    private fun subscribeToChannel(restaurantId: String) {
        val channelName = "private-restaurant.$restaurantId"
        pusher.subscribePrivate(channelName).let { channel ->
            channel.bind("order.created") { event ->
                Timber.d("Order created event received: ${event.data}")
                // TODO: Handle the event, e.g., parse the data and notify the app
            }
            channel.bind("order.updated") { event ->
                Timber.d("Order updated event received: ${event.data}")
                // TODO: Handle the event
            }
        }
    }

    fun disconnect() {
        pusher.disconnect()
    }
}
