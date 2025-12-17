package com.cagataysunal.restaurantordertracker.di

import com.cagataysunal.restaurantordertracker.data.local.TokenManager
import com.cagataysunal.restaurantordertracker.data.repository.UserRepository
import com.cagataysunal.restaurantordertracker.data.repository.UserRepositoryImpl
import com.cagataysunal.restaurantordertracker.domain.usecase.LoginUserUseCase
import com.cagataysunal.restaurantordertracker.domain.usecase.RegisterUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { TokenManager(androidContext()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory { RegisterUserUseCase(get()) }
    factory { LoginUserUseCase(get()) }
}