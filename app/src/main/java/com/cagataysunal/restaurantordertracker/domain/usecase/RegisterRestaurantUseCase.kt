package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository

class RegisterRestaurantUseCase(private val repository: RestaurantRepository) {
    suspend operator fun invoke(request: RegisterRestaurantRequest) =
        repository.registerRestaurant(request)
}
