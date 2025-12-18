package com.cagataysunal.restaurantordertracker.domain.model

data class Order(
    val id: Int,
    val orderNumber: String,
    val totalPrice: Double,
    val status: String,
    val createdAt: String
)