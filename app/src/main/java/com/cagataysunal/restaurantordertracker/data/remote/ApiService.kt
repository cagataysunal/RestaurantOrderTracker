package com.cagataysunal.restaurantordertracker.data.remote

import com.cagataysunal.restaurantordertracker.data.dto.UserRegistrationRequest

interface ApiService {
    suspend fun registerUser(request: UserRegistrationRequest): Boolean // Returns true on success
}