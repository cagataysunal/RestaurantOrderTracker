package com.cagataysunal.restaurantordertracker.di

import com.cagataysunal.restaurantordertracker.ui.home.HomeViewModel
import com.cagataysunal.restaurantordertracker.ui.login.LoginViewModel
import com.cagataysunal.restaurantordertracker.ui.registration.RegistrationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
}