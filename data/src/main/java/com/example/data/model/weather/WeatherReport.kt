package com.example.data.model.weather

import com.example.data.model.weather.domain.current.WeatherResponse
import com.example.data.model.weather.domain.forecast.ForecastWeatherResponse

data class WeatherReport(
    val currentWeather: WeatherResponse?,
    val forecastWeather: ForecastWeatherResponse?
) {
    fun isValid(): Boolean = this !== EMPTY

    companion object {
        val EMPTY = WeatherReport(null, null)
    }
}
