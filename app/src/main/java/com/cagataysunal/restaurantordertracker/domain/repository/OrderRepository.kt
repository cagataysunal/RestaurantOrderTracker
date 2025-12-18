package com.cagataysunal.restaurantordertracker.domain.repository

import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest

interface OrderRepository {
    suspend fun getOrders(): Boolean
    suspend fun updateOrderStatus(request: UpdateOrderStatusRequest): Boolean
}
