package com.cagataysunal.restaurantordertracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo
import com.cagataysunal.restaurantordertracker.domain.usecase.GetRestaurantInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getRestaurantInfoUseCase: GetRestaurantInfoUseCase) : ViewModel() {

    private val _restaurantInfoState =
        MutableStateFlow<RestaurantInfoState>(RestaurantInfoState.Loading)
    val restaurantInfoState: StateFlow<RestaurantInfoState> = _restaurantInfoState

    init {
        getRestaurantInfo()
    }

    private fun getRestaurantInfo() {
        viewModelScope.launch {
            _restaurantInfoState.value = RestaurantInfoState.Loading
            val result = getRestaurantInfoUseCase()
            _restaurantInfoState.value = result?.let {
                RestaurantInfoState.Success(it)
            } ?: RestaurantInfoState.Error("Failed to load restaurant info")
        }
    }
}

sealed class RestaurantInfoState {
    object Loading : RestaurantInfoState()
    data class Success(val restaurantInfo: RestaurantInfo) : RestaurantInfoState()
    data class Error(val message: String) : RestaurantInfoState()
}