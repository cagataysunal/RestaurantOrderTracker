package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.LoginResponse
import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterUserResponse
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantListResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest

interface ApiService {
    suspend fun registerUser(request: UserRegistrationRequest): RegisterUserResponse
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun getRestaurants(): RestaurantListResponse?
    suspend fun registerRestaurant(request: RegisterRestaurantRequest): Boolean
    suspend fun getOrders(): Boolean
}