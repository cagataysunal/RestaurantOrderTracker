package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderUpdate(
    val orderId: String,
    val customerName: String,
    val customerPhone: String,
    val customerEmail: String,
    val deliveryAddress: DeliveryAddress,
    val orderDetails: OrderDetails,
    val items: List<OrderItem>,
    val createdAt: String
)
