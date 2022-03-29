package com.example.data.model.weather.api.current

import com.google.gson.annotations.SerializedName

data class ApiWeatherDescription(
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val mainWeather: String,
    @SerializedName("description")
    val description: String
)
