package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val phone: String,
    val bussinessName: String,
    val bussinessPhone: String,
    val bussinessEmail: String
)
