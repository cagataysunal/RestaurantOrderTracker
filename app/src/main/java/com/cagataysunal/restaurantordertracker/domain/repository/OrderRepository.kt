package com.cagataysunal.restaurantordertracker.domain.repository

import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest

interface OrderRepository {
    suspend fun getOrders(): List<OrderUpdate>
    suspend fun updateOrderStatus(request: UpdateOrderStatusRequest): Boolean
}
