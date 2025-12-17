package com.cagataysunal.restaurantordertracker.data.repository

import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.domain.repository.TokenProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class TokenProviderImpl(private val sessionProvider: SessionProvider) : TokenProvider {
    override fun getToken(): String? {
        return runBlocking {
            sessionProvider.authToken.first()
        }
    }
}
