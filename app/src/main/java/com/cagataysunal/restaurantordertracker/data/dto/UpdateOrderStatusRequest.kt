package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateOrderStatusRequest(
    @SerialName("order_unique_code")
    val orderUniqueCode: String,
    val status: String
)
