package com.cagataysunal.restaurantordertracker.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val restaurantId: Int,
    val name: String,
    val email: String,
    @SerialName("bussinessName")
    val bussinessName: String,
    @SerialName("bussinessPhone")
    val bussinessPhone: String,
    @SerialName("bussinessEmail")
    val bussinessEmail: String,
    @SerialName("bussinessAddress")
    val bussinessAddress: String,
    @SerialName("bussinessCity")
    val bussinessCity: String,
    @SerialName("bussinessPostalCode")
    val bussinessPostalCode: String,
    @SerialName("bussinessCountry")
    val bussinessCountry: String,
    @SerialName("bussinessLogo")
    val bussinessLogo: String,
    @SerialName("bussinessDescription")
    val bussinessDescription: String,
    @SerialName("isCustomer")
    val isCustomer: Boolean,
    @SerialName("isSubCustomer")
    val isSubCustomer: Boolean,
    @SerialName("isAdmin")
    val isAdmin: Boolean,
    val phone: String,
)
