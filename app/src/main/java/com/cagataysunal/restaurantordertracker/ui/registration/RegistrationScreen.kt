package com.cagataysunal.restaurantordertracker.ui.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var passwordConfirmationError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var businessNameError by remember { mutableStateOf<String?>(null) }
    var businessPhoneError by remember { mutableStateOf<String?>(null) }
    var businessEmailError by remember { mutableStateOf<String?>(null) }

    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmationVisible by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        usernameError = if (username.isBlank()) "Username is required" else null
        emailError = if (email.isBlank()) "Email is required" else null
        passwordError = if (password.length < 8) "Password must be at least 8 characters" else null
        passwordConfirmationError = if (passwordConfirmation != password) "Passwords do not match" else null
        phoneError = if (phone.isBlank()) "Phone is required" else null
        businessNameError = if (businessName.isBlank()) "Business name is required" else null
        businessPhoneError = if (businessPhone.isBlank()) "Business phone is required" else null
        businessEmailError = if (businessEmail.isBlank()) "Business email is required" else null

        return usernameError == null && emailError == null && passwordError == null && passwordConfirmationError == null && phoneError == null && businessNameError == null && businessPhoneError == null && businessEmailError == null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register New User",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it; usernameError = null },
            label = { Text("Username") },
            isError = usernameError != null,
            supportingText = { usernameError?.let { Text(it) } }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = null },
            label = { Text("Email") },
            isError = emailError != null,
            supportingText = { emailError?.let { Text(it) } }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = null },
            label = { Text("Password") },
            isError = passwordError != null,
            supportingText = { passwordError?.let { Text(it) } },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it; passwordConfirmationError = null },
            label = { Text("Confirm Password") },
            isError = passwordConfirmationError != null,
            supportingText = { passwordConfirmationError?.let { Text(it) } },
            visualTransformation = if (passwordConfirmationVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (passwordConfirmationVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description =
                    if (passwordConfirmationVisible) "Hide password" else "Show password"
                IconButton(onClick = {
                    passwordConfirmationVisible = !passwordConfirmationVisible
                }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it; phoneError = null },
            label = { Text("Phone") },
            isError = phoneError != null,
            supportingText = { phoneError?.let { Text(it) } }
        )
        OutlinedTextField(
            value = businessName,
            onValueChange = { businessName = it; businessNameError = null },
            label = { Text("Business Name") },
            isError = businessNameError != null,
            supportingText = { businessNameError?.let { Text(it) } }
        )
        OutlinedTextField(
            value = businessPhone,
            onValueChange = { businessPhone = it; businessPhoneError = null },
            label = { Text("Business Phone") },
            isError = businessPhoneError != null,
            supportingText = { businessPhoneError?.let { Text(it) } }
        )
        OutlinedTextField(
            value = businessEmail,
            onValueChange = { businessEmail = it; businessEmailError = null },
            label = { Text("Business Email") },
            isError = businessEmailError != null,
            supportingText = { businessEmailError?.let { Text(it) } }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (validate()) {
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
                }
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