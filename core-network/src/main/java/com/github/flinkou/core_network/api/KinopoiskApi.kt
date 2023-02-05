package com.github.flinkou.core_network.api

import com.github.flinkou.core_network.model.response.FilmResponse
import com.github.flinkou.core_network.model.response.TopFilmsResponse

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

private const val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
private const val name = "X-API-KEY"

interface KinopoiskApi {
    @Headers("$name: $token")
    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(@QueryMap params: Map<String, String>): TopFilmsResponse

    @Headers("$name: $token")
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilm(@Path("id") id: String): FilmResponse
}