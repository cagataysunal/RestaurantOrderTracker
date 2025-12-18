package com.cagataysunal.restaurantordertracker.domain.repository

import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurantInfo(): Restaurant?
    suspend fun registerRestaurant(request: RegisterRestaurantRequest): Boolean
}
