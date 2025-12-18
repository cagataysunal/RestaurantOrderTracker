package com.cagataysunal.restaurantordertracker.ui.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.ui.navigation.Screen
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(navController: NavController, viewModel: OrdersViewModel = koinViewModel()) {
    val orders by viewModel.orders.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getOrders()
        viewModel.startListening()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopListening()
        }
    }

    if (orders.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text("No new orders... yet!")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(orders) { order ->
                OrderItem(order, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItem(order: OrderUpdate, navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            val orderJson = Json.encodeToString(order)
            navController.navigate(Screen.OrderDetail.createRoute(orderJson))
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Order #${order.uniqueCode}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Customer: ${order.customerName}")
            Text(text = "Phone: ${order.customerPhone}")
            Text(text = "Address: ${order.deliveryAddress.fullAddress}, ${order.deliveryAddress.city}")
            Text(text = "Status: ${order.orderDetails.status}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Items:",
                fontWeight = FontWeight.Bold
            )
            order.items.forEach { item ->
                Text(text = "- ${item.quantity} x ${item.productName} (${item.price} ₺)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total: ${order.orderDetails.totalAmount} ₺",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(androidx.compose.ui.Alignment.End)
            )
        }
    }
}
