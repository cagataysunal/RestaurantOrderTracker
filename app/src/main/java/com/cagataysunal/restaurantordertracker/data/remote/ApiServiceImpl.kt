package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest
import io.ktor.client.HttpClient

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun registerUser(request: UserRegistrationRequest): Boolean {
        TODO("Implement user registration")
    }
}