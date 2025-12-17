package com.cagataysunal.restaurantordertracker.ui.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cagataysunal.restaurantordertracker.ui.theme.RestaurantOrderTrackerTheme

@Composable
fun WelcomeScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToRegistration,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Started")
        }
        Spacer(modifier = Modifier.height(28.dp))
        val annotatedString = buildAnnotatedString {
            append("Already have an account? ")
            withLink(
                link = LinkAnnotation.Clickable(
                    tag = "SIGN_IN",
                    linkInteractionListener = {
                        onNavigateToLogin()
                    }
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.None
                    )
                ) {
                    append("Sign in")
                }
            }
        }
        Text(text = annotatedString)
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    RestaurantOrderTrackerTheme {
        WelcomeScreen(onNavigateToRegistration = {}, onNavigateToLogin = {})
    }
}