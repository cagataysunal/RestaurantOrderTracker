package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest

interface UserRepository {
    suspend fun registerUser(request: UserRegistrationRequest)
    suspend fun login(email: String, password: String)
}