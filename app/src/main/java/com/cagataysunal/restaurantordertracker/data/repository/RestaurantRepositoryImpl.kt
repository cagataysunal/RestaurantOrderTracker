package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(private val apiService: ApiService) : RestaurantRepository {
    override suspend fun getRestaurantInfo(): RestaurantInfo? {
        return apiService.getRestaurantInfo()
    }

    override suspend fun registerRestaurant(request: RegisterRestaurantRequest): RestaurantInfo {
        return apiService.registerRestaurant(request)
    }
}
