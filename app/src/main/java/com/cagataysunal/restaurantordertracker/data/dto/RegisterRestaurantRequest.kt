package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRestaurantRequest(
    val name: String,
    val description: String,
    val physicalAddress: String,
    val phone: String,
    val email: String,
    val city: String,
    val logo: String = "",
    val country: String,
    val mainLanguage: String,
    val supportMenuLnaguageIds: String = "",
    val operationStartTime: String,
    val operationEndTime: String,
    val cityId: String,
    val districtId: String,
    val neighborhoodId: String = ""
)
