package com.cagataysunal.restaurantordertracker.ui.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cagataysunal.restaurantordertracker.domain.model.Restaurant
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyPageScreen(viewModel: MyPageViewModel = koinViewModel()) {
    val restaurantInfoState by viewModel.restaurantInfoState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRestaurantInfo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = restaurantInfoState) {
            is RestaurantInfoState.Loading -> CircularProgressIndicator()
            is RestaurantInfoState.Success -> {
                RestaurantDetails(restaurant = state.restaurant)
            }
            is RestaurantInfoState.Error -> Text(state.message, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun RestaurantDetails(restaurant: Restaurant) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = restaurant.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(label = "Address", value = restaurant.address)
            InfoRow(label = "Phone", value = restaurant.phone)
            InfoRow(label = "Email", value = restaurant.email)
            InfoRow(label = "Operating Hours", value = restaurant.operatingHours)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            modifier = Modifier.weight(0.6f)
        )
    }
}