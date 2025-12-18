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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cagataysunal.restaurantordertracker.data.dto.DeliveryAddress
import com.cagataysunal.restaurantordertracker.data.dto.OrderDetails
import com.cagataysunal.restaurantordertracker.data.dto.OrderItem
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.ui.navigation.Screen
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderDetailScreen(
    navController: NavController,
    order: OrderUpdate,
    viewModel: OrderDetailViewModel? = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
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
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel?.acceptOrder(order.uniqueCode)
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Accept")
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = {
                        viewModel?.rejectOrder(order.uniqueCode)
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Reject")
                }
            }
            Button(
                onClick = {
                    navController.navigate(
                        Screen.Map.createRoute(
                            lat = 40.86410170504059,
                            lon = 35.61226281039344
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Show on Map")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview() {
    val mockOrder = OrderUpdate(
        orderId = "12345",
        uniqueCode = "ORD-123",
        customerName = "John Doe",
        customerPhone = "555-1234",
        customerEmail = "john.doe@example.com",
        deliveryAddress = DeliveryAddress(
            fullAddress = "123 Main St, Apt 4B",
            city = "Metropolis",
            district = "Downtown",
            neighborhood = "Central"
        ),
        orderDetails = OrderDetails(
            totalAmount = 250.0,
            discountAmount = 25.0,
            deliveryFee = 10.0,
            finalAmount = 235.0,
            paymentMethod = "Credit Card",
            paymentStatus = "Paid",
            status = "Pending",
            note = "No onions, please."
        ),
        items = listOf(
            OrderItem("Pizza", 1, 150.0, 150.0),
            OrderItem("Coke", 2, 25.0, 50.0),
            OrderItem("Fries", 1, 50.0, 50.0)
        ),
        createdAt = "2024-01-01T12:00:00Z"
    )

    RestaurantOrderTrackerTheme {
        OrderDetailScreen(
            navController = rememberNavController(),
            order = mockOrder,
            viewModel = null
        )
    }
}

@Preview(showBackground = true, name = "Long Item List Preview")
@Composable
fun OrderDetailScreenLongListPreview() {
    val mockOrder = OrderUpdate(
        orderId = "12345",
        uniqueCode = "ORD-124",
        customerName = "Jane Smith",
        customerPhone = "555-5678",
        customerEmail = "jane.smith@example.com",
        deliveryAddress = DeliveryAddress(
            fullAddress = "456 Oak Ave, Suite 200",
            city = "Gotham",
            district = "Uptown",
            neighborhood = "Riverside"
        ),
        orderDetails = OrderDetails(
            totalAmount = 850.0,
            discountAmount = 100.0,
            deliveryFee = 15.0,
            finalAmount = 765.0,
            paymentMethod = "Cash",
            paymentStatus = "Pending",
            status = "Pending",
            note = "Extra spicy on everything!"
        ),
        items = List(15) { index ->
            OrderItem("Item ${index + 1}", index + 1, 50.0, 50.0 * (index + 1))
        },
        createdAt = "2024-01-01T13:00:00Z"
    )

    RestaurantOrderTrackerTheme {
        OrderDetailScreen(
            navController = rememberNavController(),
            order = mockOrder,
            viewModel = null
        )
    }
}
