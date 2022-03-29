package com.example.data.model.weather.api.forecast

import com.google.gson.annotations.SerializedName

data class ApiWeatherMain(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_min")
    val minTemperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Double
)
