package com.github.flinkou.kinopoisktop.ui.topfilms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.flinkou.core_network.api.KinopoiskApi
import com.github.flinkou.core_network.di.NetworkComponent
import com.github.flinkou.kinopoisktop.entities.BaseViewModel
import com.github.flinkou.kinopoisktop.entities.FilmInterface
import com.github.flinkou.kinopoisktop.entities.FilmItem
import com.github.flinkou.kinopoisktop.entities.ProgressFilmItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class TopFilmsViewModel @Inject constructor(
    private val api: KinopoiskApi
) : BaseViewModel() {

    private val _data = MutableLiveData<List<FilmInterface>>()
    val data: LiveData<List<FilmInterface>> = _data

    init {
        viewModelScope.launch {
            _data.postValue(getLoaders())
            val items = getFilms()
            _data.postValue(items)
        }
    }

    private fun getLoaders() = IntRange(1, 5).map { ProgressFilmItem }

    private suspend fun getFilms(): List<FilmInterface> {
        val films = mutableListOf<FilmInterface>()

        for (i in 1..5) {
            val response = api.getTopFilms(
                mapOf(
                    "type" to "TOP_100_POPULAR_FILMS",
                    "page" to i.toString()
                )
            )
            films.addAll(response.films.map {
                val genres =
                    it.genres?.joinToString(postfix = "", separator = ", ") { genre -> genre.genre }
                        ?: ""

                val countries =
                    it.countries?.joinToString(
                        postfix = "",
                        separator = ", "
                    ) { country -> country.country }
                        ?: ""

                FilmItem(
                    id = it.id,
                    title = it.titleRu ?: it.titleEn ?: "Error",
                    year = it.year.toString(),
                    genre = genres,
                    poster = it.posterImage,
                    countries = countries,
                    description = it.description
                )
            }
            )
        }

        return films
    }

}