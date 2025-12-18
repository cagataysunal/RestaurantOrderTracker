package com.cagataysunal.restaurantordertracker.ui.orderdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderDetailScreen(
    navController: NavController,
    order: OrderUpdate,
    viewModel: OrderDetailViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Order #${order.uniqueCode}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Customer: ${order.customerName}")
        Text(text = "Phone: ${order.customerPhone}")
        Text(text = "Address: ${order.deliveryAddress.fullAddress}, ${order.deliveryAddress.city}")
        Text(text = "Status: ${order.orderDetails.status}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Items:",
            fontWeight = FontWeight.Bold
        )
        order.items.forEach { item ->
            Text(text = "- ${item.quantity} x ${item.productName} (${item.price} ₺)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total: ${order.orderDetails.totalAmount} ₺",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.acceptOrder(order.uniqueCode)
                navController.popBackStack()
            }) {
                Text(text = "Accept")
            }
            Button(onClick = {
                viewModel.rejectOrder(order.uniqueCode)
                navController.popBackStack()
            }) {
                Text(text = "Reject")
            }
        }
    }
}
