package com.cagataysunal.restaurantordertracker.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerUser(request: UserRegistrationRequest) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            val response = registerUserUseCase(request)
            _registrationState.value = if (response.success && response.user != null) {
                RegistrationState.Success(response.user.restaurantId)
            } else {
                RegistrationState.Error(response.error?.getOrNull(0) ?: response.message)
            }
        }
    }
}

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    data class Success(val restaurantId: Int) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}