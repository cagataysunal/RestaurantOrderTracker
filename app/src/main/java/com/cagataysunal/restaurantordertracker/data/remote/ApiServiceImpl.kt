package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.LoginResponse
import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterUserResponse
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantListResponse
import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
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

    override suspend fun getRestaurants(): RestaurantListResponse? {
        return try {
            client.get(ApiEndpoints.RESTAURANT).body<RestaurantListResponse>()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Failed to get restaurant info: ${e.message}")
            null
        }
    }


    override suspend fun registerRestaurant(request: RegisterRestaurantRequest): Boolean {
        return try {
            val response: HttpResponse = client.post(ApiEndpoints.RESTAURANT) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if (!response.status.isSuccess()) {
                Timber.tag(TAG).w("Restaurant registration failed with status ${response.status}: ${response.bodyAsText()}")
            }
            response.status.isSuccess()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Restaurant registration failed: ${e.message}")
            false
        }
    }

    override suspend fun getOrders(): Boolean {
        return try {
            val response: HttpResponse = client.get(ApiEndpoints.GET_ORDERS)
            if (response.status.isSuccess()) {
                Timber.d("order list request successful")
            } else {
                Timber.w("order list request failed with status ${response.status}: ${response.bodyAsText()}")
            }
            response.status.isSuccess()
        } catch (e: Exception) {
            Timber.e(e, "order list request failed: ${e.message}")
            false
        }
    }

    override suspend fun updateOrderStatus(request: UpdateOrderStatusRequest): Boolean {
        return try {
            val response: HttpResponse = client.post(ApiEndpoints.UPDATE_ORDER_STATUS) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if (!response.status.isSuccess()) {
                Timber.tag(TAG)
                    .w("Update order status failed with status ${response.status}: ${response.bodyAsText()}")
            }
            response.status.isSuccess()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Update order status failed: ${e.message}")
            false
        }
    }
}
