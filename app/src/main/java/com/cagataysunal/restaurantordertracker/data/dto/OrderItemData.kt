package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderItemData(
    val id: Int,
    val productId: Int,
    val productName: String,
    val productImage: String? = null,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val note: String? = null
)
