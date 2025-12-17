package com.cagataysunal.restaurantordertracker.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cagataysunal.restaurantordertracker.data.dto.RestaurantInfo
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val restaurantInfoState by viewModel.restaurantInfoState.collectAsState()
    HomeScreenContent(restaurantInfoState = restaurantInfoState)
}

@Composable
fun HomeScreenContent(restaurantInfoState: RestaurantInfoState) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (restaurantInfoState) {
                is RestaurantInfoState.Loading -> CircularProgressIndicator()
                is RestaurantInfoState.Success -> Text("Restaurant ID: ${restaurantInfoState.restaurantInfo.restaurantId}")
                is RestaurantInfoState.Error -> Text(restaurantInfoState.message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RestaurantOrderTrackerTheme {
        HomeScreenContent(
            restaurantInfoState = RestaurantInfoState.Success(
                RestaurantInfo(
                    restaurantId = "preview-id"
                )
            )
        )
    }
}
