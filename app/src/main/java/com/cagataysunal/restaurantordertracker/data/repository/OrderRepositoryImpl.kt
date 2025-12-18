package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.dto.OrderData
import com.cagataysunal.restaurantordertracker.data.dto.OrderDetails
import com.cagataysunal.restaurantordertracker.data.dto.OrderItem
import com.cagataysunal.restaurantordertracker.data.dto.OrderItemData
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.data.dto.UpdateOrderStatusRequest
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService) : OrderRepository {
    override suspend fun getOrders(): List<OrderUpdate> {
        return apiService.getOrders().map { it.toOrderUpdate() }
    }

    override suspend fun updateOrderStatus(request: UpdateOrderStatusRequest): Boolean {
        return apiService.updateOrderStatus(request)
    }
}

private fun OrderData.toOrderUpdate(): OrderUpdate {
    return OrderUpdate(
        orderId = this.id.toString(),
        uniqueCode = this.orderUniqueCode,
        customerName = this.customerName,
        customerPhone = this.customerPhone,
        customerEmail = this.customerEmail,
        deliveryAddress = this.deliveryAddress,
        orderDetails = OrderDetails(
            totalAmount = this.cart.totalAmount,
            discountAmount = 0.0,
            deliveryFee = 0.0,
            finalAmount = this.cart.totalAmount,
            paymentMethod = this.paymentMethod,
            paymentStatus = this.paymentStatus,
            status = this.status,
            note = this.cart.items.mapNotNull { it.note }.joinToString(", ").takeIf { it.isNotBlank() }
        ),
        items = this.cart.items.map { it.toOrderItem() },
        createdAt = this.createdAt
    )
}

private fun OrderItemData.toOrderItem(): OrderItem {
    return OrderItem(
        productName = this.productName,
        quantity = this.quantity,
        price = this.unitPrice,
        total = this.totalPrice
    )
}
