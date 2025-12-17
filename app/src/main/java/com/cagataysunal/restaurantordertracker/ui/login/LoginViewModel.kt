package com.cagataysunal.restaurantordertracker.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataysunal.restaurantordertracker.domain.model.LoginResult
import com.cagataysunal.restaurantordertracker.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            when (val result = loginUserUseCase(email, password)) {
                is LoginResult.Success -> _loginState.value =
                    LoginState.Success(result.user.restaurantId)

                is LoginResult.Fail -> _loginState.value = LoginState.Error(result.message)
                is LoginResult.Error -> _loginState.value =
                    LoginState.Error(result.exception.message ?: "An unknown error occurred")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val restaurantId: Int) : LoginState()
    data class Error(val message: String) : LoginState()
}