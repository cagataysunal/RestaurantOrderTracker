package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val email: String,
    val restaurantId: Int
)
