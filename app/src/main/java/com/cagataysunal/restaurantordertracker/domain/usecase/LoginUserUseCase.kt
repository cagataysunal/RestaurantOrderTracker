package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.repository.UserRepository
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginResult {
        return userRepository.login(email, password)
    }
}