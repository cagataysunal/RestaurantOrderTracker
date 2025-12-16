package com.cagataysunal.restaurantordertracker

import android.app.Application
import com.cagataysunal.restaurantordertracker.di.appModule
import com.cagataysunal.restaurantordertracker.di.networkModule
import com.cagataysunal.restaurantordertracker.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RestaurantOrderTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RestaurantOrderTrackerApp)
            modules(appModule, networkModule, viewModelModule)
        }
    }
}