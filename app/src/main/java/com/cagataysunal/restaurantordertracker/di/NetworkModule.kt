package com.cagataysunal.restaurantordertracker.di

import com.cagataysunal.restaurantordertracker.data.local.TokenManager
import com.cagataysunal.restaurantordertracker.data.remote.ApiService
import com.cagataysunal.restaurantordertracker.data.remote.ApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.first
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    val baseUrl = "http://188.34.155.223/new-qr-menu/"
    single {
        val tokenManager: TokenManager = get()
        HttpClient(Android) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        encodeDefaults = true
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        namingStrategy = JsonNamingStrategy.SnakeCase
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = tokenManager.authToken.first()
                        if (token != null) {
                            BearerTokens(token, "")
                        } else {
                            null
                        }
                    }
                    sendWithoutRequest { request ->
                        request.url.encodedPath.contains("/api/v1/customer/register")
                        request.url.encodedPath.contains("/api/v1/customer/login")
                    }
                }
            }
        }
    }

    single<ApiService> { ApiServiceImpl(get()) }
}