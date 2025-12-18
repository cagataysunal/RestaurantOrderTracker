package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetOrdersResponse(
    val success: Boolean,
    val message: String,
    val data: List<OrderData>
)
