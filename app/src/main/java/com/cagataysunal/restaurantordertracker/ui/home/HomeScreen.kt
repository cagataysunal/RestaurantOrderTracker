package com.cagataysunal.restaurantordertracker.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.cagataysunal.restaurantordertracker.ui.mypage.MyPageScreen
import com.cagataysunal.restaurantordertracker.ui.orders.OrdersScreen
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme

@Composable
fun HomeScreen(navController: NavController) {
    HomeScreenContent(navController)
}

@Composable
fun HomeScreenContent(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Orders", "My Page")
    val icons = listOf(Icons.AutoMirrored.Filled.List, Icons.Filled.Person)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> OrdersScreen(navController)
                1 -> MyPageScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RestaurantOrderTrackerTheme {
        // HomeScreenContent() // Requires a NavController, so we can't preview it directly
    }
}
