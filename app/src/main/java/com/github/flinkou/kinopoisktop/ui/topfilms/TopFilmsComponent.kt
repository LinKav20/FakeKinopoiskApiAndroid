package com.github.flinkou.kinopoisktop.ui.topfilms

import androidx.lifecycle.ViewModel
import com.github.flinkou.core_network.api.KinopoiskApi
import com.github.flinkou.kinopoisktop.DI
import com.github.flinkou.kinopoisktop.di.ScreenScope
import com.github.flinkou.kinopoisktop.di.ViewModelFactory
import com.github.flinkou.kinopoisktop.di.ViewModelKey
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [TopFilmsModule::class])
@ScreenScope
interface TopFilmsComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(api: KinopoiskApi): Builder

        fun build(): TopFilmsComponent
    }


    companion object {
        fun create() = with(DI.appComponent) {
            DaggerTopFilmsComponent.builder().api(DI.networkComponent.api()).build()
        }
    }

}

@Module
abstract class TopFilmsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopFilmsViewModel::class)
    abstract fun topFilmsViewModel(viewModel: TopFilmsViewModel): ViewModel

}