package com.cagataysunal.restaurantordertracker.ui.orderdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.domain.usecase.UpdateOrderStatusUseCase
import kotlinx.coroutines.launch

class OrderDetailViewModel(
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase
) : ViewModel() {

    fun acceptOrder(orderId: String) {
        viewModelScope.launch {
            updateOrderStatusUseCase(orderId, "accepted")
        }
    }

    fun rejectOrder(orderId: String) {
        viewModelScope.launch {
            updateOrderStatusUseCase(orderId, "cancelled")
        }
    }
}
