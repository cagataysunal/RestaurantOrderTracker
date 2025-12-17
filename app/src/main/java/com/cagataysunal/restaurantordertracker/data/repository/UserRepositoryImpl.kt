package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.data.local.TokenManager
import com.cagataysunal.restaurantordertracker.data.remote.ApiService

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : UserRepository {
    override suspend fun registerUser(request: UserRegistrationRequest) {
        val response = apiService.registerUser(request)
        tokenManager.saveAuthToken(response.token)
    }

    override suspend fun login(email: String, password: String) {
        val response = apiService.login(LoginRequest(email, password))
        tokenManager.saveAuthToken(response.token)
    }
}