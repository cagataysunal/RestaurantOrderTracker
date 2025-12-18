package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderData(
    val id: Int,
    val orderUniqueCode: String,
    val status: String,
    val paymentStatus: String,
    val paymentMethod: String,
    val cartId: Int,
    val customerName: String,
    val customerPhone: String,
    val customerEmail: String,
    val deliveryAddress: DeliveryAddress,
    val cart: CartData,
    val createdAt: String,
    val updatedAt: String
)
