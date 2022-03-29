package com.example.data.model.weather.api.forecast

import com.google.gson.annotations.SerializedName

data class ApiCity(
    @SerializedName("geoname_id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Int
)
