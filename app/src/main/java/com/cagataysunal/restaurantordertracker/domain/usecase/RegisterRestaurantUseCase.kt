package com.cagataysunal.restaurantordertracker.domain.usecase

import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantRequest
import com.cagataysunal.restaurantordertracker.data.dto.RegisterRestaurantResponse
import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.firstOrNull

class RegisterRestaurantUseCase(
    private val restaurantRepository: RestaurantRepository,
    private val sessionProvider: SessionProvider
) {
    suspend operator fun invoke(request: RegisterRestaurantRequest): RegisterRestaurantResponse {
        val response = restaurantRepository.registerRestaurant(request)
        if (response.success && response.restaurant != null) {
            val user = sessionProvider.user.firstOrNull()
            user?.let {
                val updatedUser = it.copy(restaurantId = response.restaurant.id)
                sessionProvider.saveUser(updatedUser)
            }
        }
        return response
    }
}
