package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    val success: Boolean,
    val message: String,
    val user: User? = null,
    val token: String? = null,
    val error: List<String>? = null
)
