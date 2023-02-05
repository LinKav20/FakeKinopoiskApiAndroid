package com.github.flinkou.core_network.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genre") val genre: String
) {
}