package com.github.flinkou.kinopoisktop

import com.github.flinkou.core_network.di.NetworkComponent
import com.github.flinkou.kinopoisktop.di.AppComponent

object DI {
    lateinit var appComponent: AppComponent
        internal set

    lateinit var networkComponent: NetworkComponent
        internal set
}