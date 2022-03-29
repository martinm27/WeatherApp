package com.example.search.ui.forecast

import androidx.annotation.DrawableRes
import com.example.coreui.util.DiffUtilViewModel

data class ForecastWeatherViewModel(
    override val id: Long,
    val dayTime: String,
    val mainWeather: String,
    val mainWeatherDescription: String,
    @DrawableRes val weatherIcon: Int,
    val temperature: String,
    val minTemperature: String,
    val maxTemperature: String,
    val pressure: String,
    val humidity: String
) : DiffUtilViewModel(id = id)
