package com.cagataysunal.restaurantordertracker.di

import com.cagataysunal.restaurantordertracker.ui.home.HomeViewModel
import com.cagataysunal.restaurantordertracker.ui.login.LoginViewModel
import com.cagataysunal.restaurantordertracker.ui.mypage.MyPageViewModel
import com.cagataysunal.restaurantordertracker.ui.orderdetail.OrderDetailViewModel
import com.cagataysunal.restaurantordertracker.ui.orders.OrdersViewModel
import com.cagataysunal.restaurantordertracker.ui.registration.RegistrationViewModel
import com.cagataysunal.restaurantordertracker.ui.restaurantregistration.RestaurantRegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { RestaurantRegistrationViewModel(get()) }
    viewModel { MyPageViewModel(get()) }
    viewModel { OrdersViewModel(get(), get(), get()) }
    viewModel { OrderDetailViewModel(get()) }
}
