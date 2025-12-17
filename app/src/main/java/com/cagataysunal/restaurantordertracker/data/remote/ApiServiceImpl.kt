package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.LoginResponse
import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterUserResponse
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import timber.log.Timber

private const val TAG = "ApiServiceImpl"

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun registerUser(request: UserRegistrationRequest): RegisterUserResponse {
        return try {
            client.post(ApiEndpoints.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<RegisterUserResponse>()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Registration failed: ${e.message}")
            RegisterUserResponse(
                success = false,
                message = e.localizedMessage ?: "An unknown error occurred",
                token = "",
                error = listOf()
            )
        }
    }

    override suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            client.post(ApiEndpoints.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<LoginResponse>()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Login failed: ${e.message}")
            LoginResponse(
                success = false,
                message = e.localizedMessage ?: "An unknown error occurred",
                token = "",
                error = listOf()
            )
        }
    }

    override suspend fun getRestaurantInfo(): RestaurantInfo? {
        return try {
            client.get(ApiEndpoints.RESTAURANT).body<RestaurantInfo>()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Failed to get restaurant info: ${e.message}")
            null
        }
    }

    override suspend fun registerRestaurant(request: RegisterRestaurantRequest): RestaurantInfo {
        return try {
            client.post(ApiEndpoints.RESTAURANT) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body<RestaurantInfo>()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Restaurant registration failed: ${e.message}")
            RestaurantInfo(restaurantId = "-1")
        }
    }
}
