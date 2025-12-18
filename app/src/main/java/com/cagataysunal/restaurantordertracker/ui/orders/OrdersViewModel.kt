package com.cagataysunal.restaurantordertracker.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.domain.usecase.GetOrdersUseCase
import kotlinx.coroutines.launch

class OrdersViewModel(private val getOrdersUseCase: GetOrdersUseCase) : ViewModel() {

    fun getOrders() {
        viewModelScope.launch {
            getOrdersUseCase()
        }
    }
}