package com.example.data.model.weather.domain.current

data class WeatherResponse(
    val weatherDescription: WeatherDescription,
    val weatherParameters: WeatherParameters,
    val visibility: Long,
    val wind: Wind,
    val cityName: String
)
