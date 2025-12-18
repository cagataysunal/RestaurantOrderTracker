package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantListResponse(
    val data: List<RestaurantDto>,
    val links: LinksDto,
    val meta: MetaDto,
    val success: Boolean,
    val message: String
)

@Serializable
data class RestaurantDto(
    val id: Int,
    val name: String,
    val slug: String,
    val email: String,
    val phone: String,
    @SerialName("physical_address") val physicalAddress: String,
    val city: String,
    val district: String,
    val country: String,
    @SerialName("operation_start_time") val operationStartTime: String,
    @SerialName("operation_end_time") val operationEndTime: String,
    val description: String,
    @SerialName("qr_code") val qrCode: String
)

@Serializable
data class LinksDto(
    val first: String?,
    val last: String?,
    val prev: String?,
    val next: String?
)

@Serializable
data class MetaDto(
    @SerialName("current_page") val currentPage: Int,
    val from: Int?,
    @SerialName("last_page") val lastPage: Int,
    val path: String,
    @SerialName("per_page") val perPage: Int,
    val to: Int?,
    val total: Int
)