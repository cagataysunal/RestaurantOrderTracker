package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.data.local.TokenManager
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : UserRepository {
    override suspend fun registerUser(request: UserRegistrationRequest): RegisterResponse {
        val response = apiService.registerUser(request)
        if (response.success && response.token != null) {
            tokenManager.saveAuthToken(response.token)
        }
        return response
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.success && response.token != null) {
                tokenManager.saveAuthToken(response.token)
                LoginResult.Success(response.token)
            } else {
                LoginResult.Fail(response.error?.getOrNull(0) ?: response.message)
            }
        } catch (e: Exception) {
            LoginResult.Error(e)
        }
    }
}