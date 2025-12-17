package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RestaurantRepositoryImpl(private val httpClient: HttpClient) : RestaurantRepository {
    override suspend fun getRestaurantInfo(): RestaurantInfo? {
        return try {
            httpClient.get("/api/v1/customer/restaurant").body<RestaurantInfo>()
        } catch (_: Exception) {
            null
        }
    }
}