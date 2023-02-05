package com.github.flinkou.core_network.model.response

import com.github.flinkou.core_network.model.FilmDto
import com.google.gson.annotations.SerializedName

data class TopFilmsResponse(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<FilmDto>
) {
}