package com.cagataysunal.restaurantordertracker.domain.repository

interface OrderRepository {
    suspend fun getOrders(): Boolean
}