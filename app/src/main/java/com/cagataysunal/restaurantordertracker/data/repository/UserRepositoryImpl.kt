package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.LoginRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterUserResponse
import com.cagataysunal.restaurantordertracker.data.dto.User
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult
import com.cagataysunal.restaurantordertracker.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val sessionProvider: SessionProvider
) : UserRepository {
    override suspend fun registerUser(request: UserRegistrationRequest): RegisterUserResponse {
        val response = apiService.registerUser(request)
        if (response.success && response.token != null && response.user != null) {
            sessionProvider.saveAuthToken(response.token)
            sessionProvider.saveUser(response.user)
        }
        return response
    }

    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.success && response.token != null && response.user != null) {
                sessionProvider.saveAuthToken(response.token)
                sessionProvider.saveUser(response.user)
                LoginResult.Success(response.token, response.user)
            } else {
                LoginResult.Fail(response.error?.getOrNull(0) ?: response.message)
            }
        } catch (e: Exception) {
            LoginResult.Error(e)
        }
    }

    override fun getLoggedInUser(): Flow<User?> {
        return sessionProvider.user
    }
}
