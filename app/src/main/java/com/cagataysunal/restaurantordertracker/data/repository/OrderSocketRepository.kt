package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import kotlinx.coroutines.flow.Flow

interface OrderSocketRepository {
    fun connect()
    fun disconnect()
    fun getOrderUpdates(): Flow<OrderUpdate>
}