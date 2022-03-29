package com.example.data.model.weather.domain.forecast

data class ForecastWeatherResponse(
    val city: City,
    val forecastData: List<ForecastWeather>
)
