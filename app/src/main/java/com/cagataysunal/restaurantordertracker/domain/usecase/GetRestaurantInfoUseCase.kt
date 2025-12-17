package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository

class GetRestaurantInfoUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke() = restaurantRepository.getRestaurantInfo()
}