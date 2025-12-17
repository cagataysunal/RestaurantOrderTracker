package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.RegisterResponse
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.domain.repository.UserRepository

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(request: UserRegistrationRequest): RegisterResponse {
        return userRepository.registerUser(request)
    }
}