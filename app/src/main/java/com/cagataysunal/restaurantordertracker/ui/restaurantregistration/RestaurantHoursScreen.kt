package com.cagataysunal.restaurantordertracker.ui.restaurantregistration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cagataysunal.restaurantordertracker.ui.navigation.Screen

@Composable
fun RestaurantHoursScreen(
    navController: NavController,
    viewModel: RestaurantRegistrationViewModel
) {
    var operationStartTime by remember { mutableStateOf(viewModel.operationStartTime) }
    var operationEndTime by remember { mutableStateOf(viewModel.operationEndTime) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is RestaurantRegistrationUIState.Success) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Welcome.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter opening and closing hours")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = operationStartTime,
            onValueChange = { operationStartTime = it },
            label = { Text("Opening Hours (HH:mm)") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = operationEndTime,
            onValueChange = { operationEndTime = it },
            label = { Text("Closing Hours (HH:mm)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.operationStartTime = operationStartTime
                viewModel.operationEndTime = operationEndTime
                viewModel.registerRestaurant()
            },
            enabled = uiState !is RestaurantRegistrationUIState.Loading
        ) {
            Text("Register")
        }

        when (uiState) {
            is RestaurantRegistrationUIState.Loading -> CircularProgressIndicator(
                modifier = Modifier.padding(
                    top = 16.dp
                )
            )

            is RestaurantRegistrationUIState.Error -> Text(
                (uiState as RestaurantRegistrationUIState.Error).message,
                modifier = Modifier.padding(top = 16.dp)
            )

            else -> {}
        }
    }
}
