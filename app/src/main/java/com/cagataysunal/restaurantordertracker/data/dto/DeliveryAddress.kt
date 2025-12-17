package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryAddress(
    val fullAddress: String,
    val city: String,
    val district: String,
    val neighborhood: String
)
