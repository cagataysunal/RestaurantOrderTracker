package com.cagataysunal.restaurantordertracker.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Login Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    RestaurantOrderTrackerTheme {
        LoginScreen()
    }
}