package com.cagataysunal.restaurantordertracker.di

import com.cagataysunal.restaurantordertracker.data.local.SessionProvider
import com.cagataysunal.restaurantordertracker.data.repository.RestaurantRepositoryImpl
import com.cagataysunal.restaurantordertracker.data.repository.UserRepositoryImpl
import com.cagataysunal.restaurantordertracker.domain.repository.RestaurantRepository
import com.cagataysunal.restaurantordertracker.domain.repository.UserRepository
import com.cagataysunal.restaurantordertracker.domain.usecase.GetRestaurantInfoUseCase
import com.cagataysunal.restaurantordertracker.domain.usecase.LoginUserUseCase
import com.cagataysunal.restaurantordertracker.domain.usecase.RegisterUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SessionProvider(androidContext()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    factory { RegisterUserUseCase(get()) }
    factory { LoginUserUseCase(get()) }
    factory { GetRestaurantInfoUseCase(get()) }
}