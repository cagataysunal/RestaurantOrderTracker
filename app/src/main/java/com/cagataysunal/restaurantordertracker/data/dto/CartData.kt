package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartData(
    val id: Int,
    val restaurantId: Int,
    val restaurantName: String,
    val userId: Int,
    val cartUniqueCode: String,
    val totalAmount: Double,
    val items: List<OrderItemData>
)
