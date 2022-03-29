package com.example.data.model.weather.domain.forecast

import com.example.data.model.weather.domain.current.WeatherDescription

data class ForecastWeather(
    val dayTime: Long,
    val weatherMain: WeatherMain,
    val weatherDescription: List<WeatherDescription>,
    val dayTimeText: String
)
