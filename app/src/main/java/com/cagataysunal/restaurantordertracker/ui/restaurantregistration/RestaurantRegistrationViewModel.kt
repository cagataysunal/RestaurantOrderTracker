package com.cagataysunal.restaurantordertracker.ui.restaurantregistration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.domain.usecase.RegisterRestaurantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantRegistrationViewModel(
    private val registerRestaurantUseCase: RegisterRestaurantUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<RestaurantRegistrationUIState>(RestaurantRegistrationUIState.Idle)
    val uiState = _uiState.asStateFlow()

    var name = ""
    var description = ""
    var email = ""
    var phone = ""
    var physicalAddress = ""
    var city = ""
    var cityId = ""
    var districtId = ""
    var country = ""
    var language = ""
    var operationStartTime = ""
    var operationEndTime = ""

    fun registerRestaurant() {
        val request = RegisterRestaurantRequest(
            name = name,
            description = description,
            email = email,
            phone = phone,
            physicalAddress = physicalAddress,
            city = city,
            cityId = cityId,
            districtId = districtId,
            country = country,
            mainLanguage = language,
            operationStartTime = operationStartTime,
            operationEndTime = operationEndTime
        )

        viewModelScope.launch {
            _uiState.value = RestaurantRegistrationUIState.Loading
            val response = registerRestaurantUseCase(request)
            if (response.success) {
                _uiState.value = RestaurantRegistrationUIState.Success
            } else {
                _uiState.value = RestaurantRegistrationUIState.Error(response.message)
            }
        }
    }
}

sealed class RestaurantRegistrationUIState {
    object Idle : RestaurantRegistrationUIState()
    object Loading : RestaurantRegistrationUIState()
    object Success : RestaurantRegistrationUIState()
    data class Error(val message: String) : RestaurantRegistrationUIState()
}
