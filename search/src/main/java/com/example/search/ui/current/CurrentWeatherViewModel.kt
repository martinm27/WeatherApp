package com.example.search.ui.current

import androidx.annotation.DrawableRes

data class CurrentWeatherViewModel(
    val cityName: String,
    val mainWeather: String,
    val mainWeatherDescription: String,
    @DrawableRes val weatherIcon: Int,
    val temperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val pressure: String,
    val humidity: String
)
