package com.cagataysunal.restaurantordertracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cagataysunal.restaurantordertracker.ui.login.LoginScreen
import com.cagataysunal.restaurantordertracker.ui.registration.RegistrationScreen
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme
import com.cagataysunal.restaurantordertracker.ui.welcome.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Registration : Screen("registration")
    object Login : Screen("login")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToRegistration = { navController.navigate(Screen.Registration.route) },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen()
        }
        composable(Screen.Login.route) {
            LoginScreen()
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