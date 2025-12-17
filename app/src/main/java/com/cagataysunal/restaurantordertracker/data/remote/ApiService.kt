package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.LoginResponse
import com.cagataysunal.restaurantordertracker.data.dto.RegisterResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest

interface ApiService {
    suspend fun registerUser(request: UserRegistrationRequest): RegisterResponse
    suspend fun login(request: LoginRequest): LoginResponse
}