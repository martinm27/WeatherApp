package com.example.data.model.weather.api.current

import com.google.gson.annotations.SerializedName

data class ApiWeatherResponse(
    @SerializedName("weather")
    val apiWeatherDescriptions: List<ApiWeatherDescription>,
    @SerializedName("main")
    val apiWeatherParameters: ApiWeatherParameters,
    @SerializedName("visibility")
    val visibility: Long,
    @SerializedName("wind")
    val apiWind: ApiWind,
    @SerializedName("name")
    val cityName: String
)
