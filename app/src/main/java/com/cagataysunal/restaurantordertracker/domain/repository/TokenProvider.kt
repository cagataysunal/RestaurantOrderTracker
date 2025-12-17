package com.cagataysunal.restaurantordertracker.domain.repository

interface TokenProvider {
    suspend fun getToken(): String?
}
