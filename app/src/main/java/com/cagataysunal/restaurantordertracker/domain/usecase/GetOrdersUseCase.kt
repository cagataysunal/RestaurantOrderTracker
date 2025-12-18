package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.domain.repository.OrderRepository

class GetOrdersUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(): List<OrderUpdate> = orderRepository.getOrders()
}
