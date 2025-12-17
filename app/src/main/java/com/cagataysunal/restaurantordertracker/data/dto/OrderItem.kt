package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(
    val productName: String,
    val quantity: Int,
    val price: Double,
    val total: Double
)
