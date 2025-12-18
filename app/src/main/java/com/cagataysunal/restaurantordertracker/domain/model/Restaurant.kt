package com.cagataysunal.restaurantordertracker.domain.model

data class Restaurant(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val operatingHours: String,
    val description: String,
    val qrCodeUrl: String
)