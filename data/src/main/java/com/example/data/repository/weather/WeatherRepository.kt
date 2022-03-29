package com.example.data.repository.weather

import com.example.data.model.weather.WeatherReport
import io.reactivex.Flowable

const val API_KEY = "d1bdbfdc0dd2193c671e9aa646ee189b"

interface WeatherRepository {

    fun getWeatherReportForCity(): Flowable<WeatherReport>

    fun searchWeatherForCity(cityName: String)

}
