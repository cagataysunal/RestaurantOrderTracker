package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null,
    val error: List<String>? = null
)