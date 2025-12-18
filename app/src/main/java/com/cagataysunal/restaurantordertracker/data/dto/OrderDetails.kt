package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderDetails(
    val totalAmount: Double,
    val discountAmount: Double,
    val deliveryFee: Double,
    val finalAmount: Double,
    val paymentMethod: String,
    val paymentStatus: String,
    val status: String,
    val note: String?
)
