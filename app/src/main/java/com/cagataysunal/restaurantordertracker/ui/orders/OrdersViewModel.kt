package com.cagataysunal.restaurantordertracker.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.data.websocket.PusherManager
import com.cagataysunal.restaurantordertracker.domain.usecase.GetOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val pusherManager: PusherManager,
    private val sessionProvider: SessionProvider
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrderUpdate>>(emptyList())
    val orders = _orders.asStateFlow()

    init {
        listenForOrderUpdates()
    }

    fun getOrders() {
        viewModelScope.launch {
            _orders.value = getOrdersUseCase()
        }
    }

    fun startListening() {
        viewModelScope.launch {
            val user = sessionProvider.user.firstOrNull()
            user?.restaurantId?.toString()?.let {
                pusherManager.init(it)
            }
        }
    }

    private fun listenForOrderUpdates() {
        pusherManager.orderUpdates
            .onEach { newOrder ->
                if (_orders.value.none { it.orderId == newOrder.orderId }) {
                    _orders.value += newOrder
                }
            }
            .launchIn(viewModelScope)
    }

    fun stopListening() {
        pusherManager.disconnect()
    }

    override fun onCleared() {
        super.onCleared()
        stopListening()
    }
}
