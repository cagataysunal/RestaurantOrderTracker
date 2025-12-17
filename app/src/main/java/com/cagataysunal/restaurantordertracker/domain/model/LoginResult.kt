package com.cagataysunal.restaurantordertracker.domain.model

import com.cagataysunal.restaurantordertracker.data.dto.User

sealed class LoginResult {
    data class Success(val token: String, val user: User) : LoginResult()
    data class Fail(val message: String) : LoginResult()
    data class Error(val exception: Throwable) : LoginResult()
}