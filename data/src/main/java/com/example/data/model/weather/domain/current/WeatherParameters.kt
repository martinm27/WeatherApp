package com.example.data.model.weather.domain.current

data class WeatherParameters(
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val minTemperature: Double,
    val maxTemperature: Double
)
