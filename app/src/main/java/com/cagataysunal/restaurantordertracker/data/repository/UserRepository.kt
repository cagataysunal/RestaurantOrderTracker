package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.RegisterResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult

interface UserRepository {
    suspend fun registerUser(request: UserRegistrationRequest): RegisterResponse
    suspend fun login(email: String, password: String): LoginResult
}