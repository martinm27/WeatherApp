package com.example.data.model.weather.domain.forecast

data class WeatherMain(
    val temperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Double,
    val humidity: Double
)
