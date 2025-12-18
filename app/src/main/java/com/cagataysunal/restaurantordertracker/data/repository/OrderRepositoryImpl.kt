package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService) : OrderRepository {
    override suspend fun getOrders(): Boolean {
        return apiService.getOrders()
    }
}