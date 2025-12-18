package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRestaurantResponse(
    val success: Boolean,
    val message: String,
    val restaurant: RestaurantInfo? = null
)

@Serializable
data class RestaurantInfo(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)
