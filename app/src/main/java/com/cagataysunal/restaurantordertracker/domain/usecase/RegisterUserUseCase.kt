package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.data.repository.UserRepository

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(request: UserRegistrationRequest): Boolean {
        return userRepository.registerUser(request)
    }
}