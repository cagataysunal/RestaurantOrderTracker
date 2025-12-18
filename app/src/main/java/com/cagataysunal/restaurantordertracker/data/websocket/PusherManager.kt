package com.cagataysunal.restaurantordertracker.data.websocket

import com.cagataysunal.restaurantordertracker.BuildConfig
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.pusher.client.ChannelAuthorizer
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.PrivateChannelEventListener
import com.pusher.client.channel.PusherEvent
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import timber.log.Timber

class CustomAuthorizer(private val client: HttpClient) : ChannelAuthorizer {
    override fun authorize(channelName: String, socketId: String): String {
        return try {
            runBlocking {
                val response: HttpResponse =
                    client.post("http://188.34.155.223/new-qr-menu/api/broadcasting/auth") {
                        contentType(ContentType.Application.Json)
                        header("X-Requested-With", "XMLHttpRequest")
                        setBody(mapOf("socket_id" to socketId, "channel_name" to channelName))
                    }
                response.bodyAsText()
            }
        } catch (e: Exception) {
            Timber.e(e, "Pusher authentication failed")
            ""
        }
    }
}

private const val HOST = "188.34.155.223"
private const val WS_PORT = 6001

class PusherManager(private val httpClient: HttpClient) {
    private lateinit var pusher: Pusher
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _orderUpdates = MutableSharedFlow<OrderUpdate>()
    val orderUpdates = _orderUpdates.asSharedFlow()

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }

    fun init(restaurantId: String) {
        val authorizer = CustomAuthorizer(httpClient)

        val options = PusherOptions().apply {
            setHost(HOST)
            setWsPort(WS_PORT)
            setUseTLS(false)
            channelAuthorizer = authorizer
        }

        pusher = Pusher(BuildConfig.PUSHER_API_KEY, options)

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

        val listener = object : PrivateChannelEventListener {
            override fun onEvent(event: PusherEvent) {
                if (event.eventName == "order.created") {
                    Timber.d("Order created event received: ${event.data}")
                    try {
                        val orderUpdate = json.decodeFromString<OrderUpdate>(event.data)
                        coroutineScope.launch {
                            _orderUpdates.emit(orderUpdate)
                        }
                    } catch (e: Exception) {
                        Timber.e(e, "Error parsing order update")
                    }
                }
            }

            override fun onAuthenticationFailure(message: String, e: Exception) {
                Timber.e(e, "Pusher authentication failed for channel $channelName: $message")
            }

            override fun onSubscriptionSucceeded(channelName: String) {
                Timber.d("Successfully subscribed to private channel: $channelName")
            }
        }

        pusher.subscribePrivate(channelName, listener, "order.created")
    }

    fun disconnect() {
        if (::pusher.isInitialized) {
            pusher.disconnect()
        }
    }
}