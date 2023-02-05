package com.github.flinkou.kinopoisktop

import android.app.Application
import com.github.flinkou.core_network.di.DaggerNetworkComponent
import com.github.flinkou.kinopoisktop.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DI.appComponent = DaggerAppComponent.builder().appContext(this).build()
        DI.networkComponent = DaggerNetworkComponent.create()
    }
}