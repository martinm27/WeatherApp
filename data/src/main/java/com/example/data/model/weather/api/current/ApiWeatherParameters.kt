package com.example.data.model.weather.api.current

import com.google.gson.annotations.SerializedName

data class ApiWeatherParameters(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temp_min")
    val minTemperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double
)
