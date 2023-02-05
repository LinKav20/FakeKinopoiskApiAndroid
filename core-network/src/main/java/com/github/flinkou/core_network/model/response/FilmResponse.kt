package com.github.flinkou.core_network.model.response

import com.github.flinkou.core_network.model.Country
import com.github.flinkou.core_network.model.Genre
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameEn") val titleEn: String?,
    @SerializedName("nameRu") val titleRu: String?,
    @SerializedName("posterUrl") val posterImage: String,
    @SerializedName("year") val year: Int,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("countries") val countries: List<Country>?,
) {
}