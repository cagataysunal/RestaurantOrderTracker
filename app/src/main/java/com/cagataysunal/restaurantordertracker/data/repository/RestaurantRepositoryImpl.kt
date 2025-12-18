package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.mapper.toDomain
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.model.Restaurant
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(private val apiService: ApiService) : RestaurantRepository {
    override suspend fun getRestaurantInfo(): Restaurant? {
        val response = apiService.getRestaurants()
        return response?.data?.firstOrNull()?.toDomain()
    }

    override suspend fun registerRestaurant(request: RegisterRestaurantRequest): Boolean {
        return apiService.registerRestaurant(request)
    }
}
