package com.github.flinkou.core_network.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country") val country: String
) {
}