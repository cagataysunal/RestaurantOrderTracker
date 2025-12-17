package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val phone: String,
    val businessName: String,
    val businessPhone: String,
    val businessEmail: String
)
