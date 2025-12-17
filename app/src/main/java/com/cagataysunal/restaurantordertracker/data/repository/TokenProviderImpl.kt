package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.local.TokenManager
import com.cagataysunal.restaurantordertracker.domain.repository.TokenProvider
import kotlinx.coroutines.flow.first

class TokenProviderImpl(private val tokenManager: TokenManager) : TokenProvider {
    override suspend fun getToken(): String? {
        return tokenManager.authToken.first()
    }
}
