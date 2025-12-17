package com.cagataysunal.restaurantordertracker.domain.repository

import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo

interface RestaurantRepository {
    suspend fun getRestaurantInfo(): RestaurantInfo?
}