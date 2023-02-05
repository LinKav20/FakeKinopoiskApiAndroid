package com.github.flinkou.kinopoisktop.ui.filminfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.flinkou.core_network.api.KinopoiskApi
import com.github.flinkou.kinopoisktop.entities.BaseViewModel
import com.github.flinkou.kinopoisktop.entities.FilmInterface
import com.github.flinkou.kinopoisktop.entities.FilmItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmInfoViewModel @Inject constructor(
    private val api: KinopoiskApi
) : BaseViewModel() {

    private val _film = MutableLiveData<FilmItem>()
    val film: LiveData<FilmItem> = _film

    fun loadFilm(ID: String) {
        viewModelScope.launch {
            val value = getFilm(ID)
            _film.postValue(value)
        }
    }

    private suspend fun getFilm(id: String): FilmItem {
        val response = api.getFilm(id)

        val genres =
            response.genres?.joinToString(postfix = "", separator = ", ") { genre -> genre.genre }
                ?: ""

        val countries =
            response.countries?.joinToString(
                postfix = "",
                separator = ", "
            ) { country -> country.country }
                ?: ""

        return FilmItem(
            id = response.id,
            title = response.titleRu ?: response.titleEn ?: "Error",
            year = response.year.toString(),
            genre = genres,
            poster = response.posterImage,
            countries = countries,
            description = response.description
        )
    }
}