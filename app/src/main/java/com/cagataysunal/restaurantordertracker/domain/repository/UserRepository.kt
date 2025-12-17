package com.cagataysunal.restaurantordertracker.domain.repository

import com.cagataysunal.restaurantordertracker.data.dto.RegisterUserResponse
import com.cagataysunal.restaurantordertracker.data.dto.User
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(request: UserRegistrationRequest): RegisterUserResponse
    suspend fun login(email: String, password: String): LoginResult
    fun getLoggedInUser(): Flow<User?>
}
