package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest
import com.cagataysunal.restaurantordertracker.domain.repository.OrderRepository

open class UpdateOrderStatusUseCase(private val orderRepository: OrderRepository) {
    open suspend operator fun invoke(orderId: String, status: String): Boolean {
        val request = UpdateOrderStatusRequest(orderUniqueCode = orderId, status = status)
        return orderRepository.updateOrderStatus(request)
    }
}
