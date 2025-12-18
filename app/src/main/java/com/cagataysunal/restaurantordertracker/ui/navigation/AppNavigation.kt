package com.cagataysunal.restaurantordertracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cagataysunal.restaurantordertracker.data.dto.OrderUpdate
import com.cagataysunal.restaurantordertracker.ui.home.HomeScreen
import com.cagataysunal.restaurantordertracker.ui.login.LoginScreen
import com.cagataysunal.restaurantordertracker.ui.map.MapScreen
import com.cagataysunal.restaurantordertracker.ui.orderdetail.OrderDetailScreen
import com.cagataysunal.restaurantordertracker.ui.registration.RegistrationScreen
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantContactScreen
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantHoursScreen
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantNameScreen
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantPromptScreen
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantRegistrationViewModel
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme
import com.cagataysunal.restaurantordertracker.ui.welcome.WelcomeScreen
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Registration : Screen("registration")
    object Login : Screen("login")
    object Home : Screen("home")
    object RestaurantPrompt : Screen("restaurant_prompt")
    object RestaurantName : Screen("restaurant_name")
    object RestaurantContact : Screen("restaurant_contact")
    object RestaurantHours : Screen("restaurant_hours")
    object OrderDetail : Screen("order_detail/{order}") {
        fun createRoute(order: String) = "order_detail/$order"
    }

    object Map : Screen("map/{lat}/{lon}") {
        fun createRoute(lat: Double, lon: Double) = "map/$lat/$lon"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val restaurantViewModel: RestaurantRegistrationViewModel = koinViewModel()
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToRegistration = { navController.navigate(Screen.Registration.route) },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                onRegistrationSuccess = { restaurantId ->
                    if (restaurantId == 0) {
                        navController.navigate(Screen.RestaurantPrompt.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { restaurantId ->
                    if (restaurantId == 0) {
                        navController.navigate(Screen.RestaurantPrompt.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.RestaurantPrompt.route) {
            RestaurantPromptScreen(navController)
        }
        composable(Screen.RestaurantName.route) {
            RestaurantNameScreen(navController, restaurantViewModel)
        }
        composable(Screen.RestaurantContact.route) {
            RestaurantContactScreen(navController, restaurantViewModel)
        }
        composable(Screen.RestaurantHours.route) {
            RestaurantHoursScreen(navController, restaurantViewModel)
        }
        composable(Screen.OrderDetail.route) { backStackEntry ->
            val orderJson = backStackEntry.arguments?.getString("order")
            val order = orderJson?.let { Json.decodeFromString<OrderUpdate>(it) }
            if (order != null) {
                OrderDetailScreen(navController, order)
            }
        }
        composable(
            Screen.Map.route,
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType })
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val lon = backStackEntry.arguments?.getFloat("lon")?.toDouble() ?: 0.0
            MapScreen(lat = lat, lon = lon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    RestaurantOrderTrackerTheme {
        AppNavigation()
    }
}
