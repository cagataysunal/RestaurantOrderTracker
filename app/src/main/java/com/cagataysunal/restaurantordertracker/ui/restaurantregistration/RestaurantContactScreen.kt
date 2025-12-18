package com.cagataysunal.restaurantordertracker.ui.restaurantregistration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantContactScreen(onNext: () -> Unit, viewModel: RestaurantRegistrationViewModel) {
    var email by remember { mutableStateOf(viewModel.email) }
    var phone by remember { mutableStateOf(viewModel.phone) }
    var address by remember { mutableStateOf(viewModel.physicalAddress) }
    var city by remember { mutableStateOf(viewModel.city) }
    var cityId by remember { mutableStateOf(viewModel.cityId) }
    var districtId by remember { mutableStateOf(viewModel.districtId) }
    var country by remember { mutableStateOf(viewModel.country) }
    var language by remember { mutableStateOf(viewModel.language) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter contact information")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
        OutlinedTextField(value = city, onValueChange = { city = it }, label = { Text("City") })
        OutlinedTextField(value = cityId, onValueChange = { cityId = it }, label = { Text("City ID") })
        OutlinedTextField(value = districtId, onValueChange = { districtId = it }, label = { Text("District ID") })
        OutlinedTextField(value = country, onValueChange = { country = it }, label = { Text("Country") })
        OutlinedTextField(value = language, onValueChange = { language = it }, label = { Text("Language") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.email = email
            viewModel.phone = phone
            viewModel.physicalAddress = address
            viewModel.city = city
            viewModel.cityId = cityId
            viewModel.districtId = districtId
            viewModel.country = country
            viewModel.language = language
            onNext()
        }) {
            Text("Next")
        }
    }
}
