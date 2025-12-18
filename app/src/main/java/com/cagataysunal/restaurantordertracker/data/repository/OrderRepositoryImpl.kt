package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService) : OrderRepository {
    override suspend fun getOrders(): Boolean {
        return apiService.getOrders()
    }

    override suspend fun updateOrderStatus(request: UpdateOrderStatusRequest): Boolean {
        return apiService.updateOrderStatus(request)
    }
}
