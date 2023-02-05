package com.github.flinkou.kinopoisktop.ui.filminfo

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


@Component(modules=[FilmInfoModule::class])
@ScreenScope
interface FilmInfoComponent {
    fun viewModelFactory():ViewModelFactory

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun api(api:KinopoiskApi):Builder

        fun build():FilmInfoComponent
    }

    companion object{
        fun create() = with(DI.appComponent){
            DaggerFilmInfoComponent.builder().api(DI.networkComponent.api()).build()
        }
    }
}


@Module
abstract class FilmInfoModule{

    @Binds
    @IntoMap
    @ViewModelKey(FilmInfoViewModel::class)
    abstract fun filmInfoViewModel(viewModel: FilmInfoViewModel):ViewModel
}

