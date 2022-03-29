package com.example.data.model.weather.api.forecast

import com.example.data.model.weather.api.current.ApiWeatherDescription
import com.google.gson.annotations.SerializedName

data class ApiForecastWeather(
    @SerializedName("dt")
    val dayTime: Long,
    @SerializedName("main")
    val apiWeatherMain: ApiWeatherMain,
    @SerializedName("weather")
    val apiWeatherDescriptions: List<ApiWeatherDescription>,
    @SerializedName("dt_txt")
    val dayTimeText: String
)
