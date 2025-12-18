package com.cagataysunal.restaurantordertracker.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import com.cagataysunal.restaurantordertracker.ui.mypage.MyPageScreen
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Orders", "My Page", "Options")
    val icons = listOf(Icons.AutoMirrored.Filled.List, Icons.Filled.Person, Icons.Filled.Settings)

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
                0 -> Text("Orders Screen") // Replace with your Orders screen
                1 -> MyPageScreen()
                2 -> Text("Options Screen") // Replace with your Options screen
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RestaurantOrderTrackerTheme {
        HomeScreenContent()
    }
}
