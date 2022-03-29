package com.example.search.ui

import com.example.data.model.weather.domain.current.WeatherResponse
import com.example.data.model.weather.domain.forecast.ForecastWeather
import com.example.search.R
import com.example.search.ui.current.CurrentWeatherViewModel
import com.example.search.ui.forecast.ForecastWeatherViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

private const val UNKNOWN = "N/A"
private const val CELSIUS = "°C"
private const val PERCENTAGE = "%"

val df = DecimalFormat("#.#").apply {
    roundingMode = RoundingMode.CEILING
}

fun toForecastWeatherViewModel(forecastWeather: ForecastWeather?): ForecastWeatherViewModel =
    with(forecastWeather) {
        ForecastWeatherViewModel(
            this?.dayTime ?: -1,
            this?.dayTimeText ?: UNKNOWN,
            this?.weatherDescription?.firstOrNull()?.mainWeather ?: UNKNOWN,
            this?.weatherDescription?.firstOrNull()?.description ?: UNKNOWN,
            if (this?.weatherDescription?.firstOrNull()?.mainWeather?.toLowerCase() == "clear") R.drawable.ic_sun else R.drawable.ic_cloudy,
            if (this?.weatherMain?.temperature != null) df.format(this.weatherMain.temperature).toString() + CELSIUS else UNKNOWN,
            if (this?.weatherMain?.minTemperature != null) df.format(this.weatherMain.minTemperature).toString() + CELSIUS else UNKNOWN,
            if (this?.weatherMain?.maxTemperature != null) df.format(this.weatherMain.maxTemperature).toString() + CELSIUS else UNKNOWN,
            this?.weatherMain?.pressure?.toString() ?: UNKNOWN,
            if (this?.weatherMain?.humidity != null) this.weatherMain.humidity.toString() + PERCENTAGE else UNKNOWN
        )
    }

fun toCurrentWeatherViewModel(currentWeather: WeatherResponse?): CurrentWeatherViewModel =
    with(currentWeather) {
        CurrentWeatherViewModel(
            this?.cityName ?: UNKNOWN,
            this?.weatherDescription?.mainWeather ?: UNKNOWN,
            this?.weatherDescription?.description ?: UNKNOWN,
            if (this?.weatherDescription?.mainWeather?.toLowerCase() == "clear") R.drawable.ic_sun else R.drawable.ic_cloudy,
            if (this?.weatherParameters?.temperature != null) df.format(this.weatherParameters.temperature).toString() + CELSIUS else UNKNOWN,
            if (this?.weatherParameters?.maxTemperature != null) df.format(this.weatherParameters.maxTemperature).toString() + CELSIUS else UNKNOWN,
            if (this?.weatherParameters?.minTemperature != null) df.format(this.weatherParameters.minTemperature).toString() + CELSIUS else UNKNOWN,
            this?.weatherParameters?.pressure?.toString() ?: UNKNOWN,
            if (this?.weatherParameters?.humidity != null) this.weatherParameters.humidity.toString() + PERCENTAGE else UNKNOWN
        )
    }
