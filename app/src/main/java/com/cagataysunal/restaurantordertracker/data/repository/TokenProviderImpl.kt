package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.domain.repository.TokenProvider
import kotlinx.coroutines.flow.first

class TokenProviderImpl(private val sessionProvider: SessionProvider) : TokenProvider {
    override suspend fun getToken(): String? {
        return sessionProvider.authToken.first()
    }
}
