package com.cagataysunal.restaurantordertracker.data.remote

import android.util.Log
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun registerUser(request: UserRegistrationRequest): Boolean {
        return try {
            val response: HttpResponse = client.post("api/v1/customer/register") {
                setBody(request)
            }
            response.status.isSuccess()
        } catch (e: Exception) {
            Log.e("ApiServiceImpl", "Registration failed: ${e.message}", e)
            false
        }
    }
}
