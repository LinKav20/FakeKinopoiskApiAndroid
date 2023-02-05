package com.github.flinkou.kinopoisktop.entities

data class FilmItem(
    override val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val poster: String,
    val countries: String,
    val description: String?
) : FilmInterface {
}