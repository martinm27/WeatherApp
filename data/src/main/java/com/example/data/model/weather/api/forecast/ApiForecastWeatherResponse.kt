package com.example.data.model.weather.api.forecast

import com.google.gson.annotations.SerializedName

data class ApiForecastWeatherResponse(
    @SerializedName("list")
    val apiForecastData: List<ApiForecastWeather>,
    @SerializedName("city")
    val apiCity: ApiCity
)
