package com.cagataysunal.restaurantordertracker.domain.model

sealed class LoginResult {
    data class Success(val token: String) : LoginResult()
    data class Fail(val message: String) : LoginResult()
    data class Error(val exception: Throwable) : LoginResult()
}