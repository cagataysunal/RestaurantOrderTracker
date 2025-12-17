package com.cagataysunal.restaurantordertracker.data.remote

import android.util.Log
import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.LoginResponse
import com.cagataysunal.restaurantordertracker.data.dto.RegisterResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun registerUser(request: UserRegistrationRequest): RegisterResponse {
        return try {
            val response: HttpResponse = client.post("api/v1/customer/register") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            response.body<RegisterResponse>()
        } catch (e: Exception) {
            Log.e("ApiServiceImpl", "Registration failed: ${e.message}", e)
            RegisterResponse(
                success = false,
                message = e.localizedMessage ?: "An unknown error occurred",
                token = ""
            )
        }
    }

    override suspend fun login(request: LoginRequest): LoginResponse {
        return client.post("api/v1/customer/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<LoginResponse>()
    }
}