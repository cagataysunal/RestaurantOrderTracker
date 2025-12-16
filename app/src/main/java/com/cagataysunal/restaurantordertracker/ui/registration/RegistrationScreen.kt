package com.cagataysunal.restaurantordertracker.ui.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val registrationState by viewModel.registrationState.collectAsState()
    RegistrationContent(
        registrationState = registrationState,
        onRegister = { request -> viewModel.registerUser(request) }
    )
}

@Composable
fun RegistrationContent(
    registrationState: RegistrationState,
    onRegister: (UserRegistrationRequest) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var businessName by remember { mutableStateOf("") }
    var businessPhone by remember { mutableStateOf("") }
    var businessEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it },
            label = { Text("Confirm Password") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = businessName,
            onValueChange = { businessName = it },
            label = { Text("Business Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = businessPhone,
            onValueChange = { businessPhone = it },
            label = { Text("Business Phone") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = businessEmail,
            onValueChange = { businessEmail = it },
            label = { Text("Business Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val request = UserRegistrationRequest(
                    username,
                    email,
                    password,
                    passwordConfirmation,
                    phone,
                    businessName,
                    businessPhone,
                    businessEmail
                )
                onRegister(request)
            },
            enabled = registrationState != RegistrationState.Loading
        ) {
            Text("Register")
        }

        when (registrationState) {
            is RegistrationState.Loading -> CircularProgressIndicator(
                modifier = Modifier.padding(
                    top = 16.dp
                )
            )

            is RegistrationState.Success -> Text(
                "Registration successful!",
                modifier = Modifier.padding(top = 16.dp)
            )

            is RegistrationState.Error -> Text(
                registrationState.message,
                modifier = Modifier.padding(top = 16.dp)
            )

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RestaurantOrderTrackerTheme {
        RegistrationContent(
            registrationState = RegistrationState.Idle,
            onRegister = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenLoadingPreview() {
    RestaurantOrderTrackerTheme {
        RegistrationContent(
            registrationState = RegistrationState.Loading,
            onRegister = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenSuccessPreview() {
    RestaurantOrderTrackerTheme {
        RegistrationContent(
            registrationState = RegistrationState.Success,
            onRegister = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenErrorPreview() {
    RestaurantOrderTrackerTheme {
        RegistrationContent(
            registrationState = RegistrationState.Error("Something went wrong"),
            onRegister = {}
        )
    }
}