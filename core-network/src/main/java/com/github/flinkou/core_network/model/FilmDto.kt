package com.github.flinkou.core_network.model

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("filmId") val id: Int,
    @SerializedName("nameEn") val titleEn: String?,
    @SerializedName("nameRu") val titleRu: String?,
    @SerializedName("posterUrl") val posterImage: String,
    @SerializedName("year") val year: Int,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("countries") val countries: List<Country>?,
) {
}