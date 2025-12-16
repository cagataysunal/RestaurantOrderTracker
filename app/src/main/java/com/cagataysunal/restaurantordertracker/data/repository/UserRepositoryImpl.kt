package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.data.remote.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun registerUser(request: UserRegistrationRequest): Boolean {
        return apiService.registerUser(request)
    }
}