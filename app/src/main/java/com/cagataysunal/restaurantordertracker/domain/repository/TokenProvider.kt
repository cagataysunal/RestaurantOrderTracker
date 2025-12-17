package com.cagataysunal.restaurantordertracker.domain.repository

interface TokenProvider {
    fun getToken(): String?
}
