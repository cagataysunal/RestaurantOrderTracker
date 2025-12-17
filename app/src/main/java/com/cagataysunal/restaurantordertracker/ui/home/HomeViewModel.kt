package com.cagataysunal.restaurantordertracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.data.websocket.PusherManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pusherManager: PusherManager,
    private val sessionProvider: SessionProvider
) : ViewModel() {
    init {
        initializePusher()
    }

    private fun initializePusher() {
        viewModelScope.launch {
            val user = sessionProvider.user.firstOrNull()
            if (user?.restaurantId != null) {
                val restaurantId = user.restaurantId.toString()
                pusherManager.init(restaurantId)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        pusherManager.disconnect()
    }
}