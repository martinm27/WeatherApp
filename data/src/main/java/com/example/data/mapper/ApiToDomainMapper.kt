package com.example.data.mapper

import com.example.data.model.weather.WeatherReport
import com.example.data.model.weather.api.current.ApiWeatherDescription
import com.example.data.model.weather.api.current.ApiWeatherParameters
import com.example.data.model.weather.api.current.ApiWeatherResponse
import com.example.data.model.weather.api.current.ApiWind
import com.example.data.model.weather.api.forecast.ApiCity
import com.example.data.model.weather.api.forecast.ApiForecastWeather
import com.example.data.model.weather.api.forecast.ApiForecastWeatherResponse
import com.example.data.model.weather.api.forecast.ApiWeatherMain
import com.example.data.model.weather.domain.current.WeatherDescription
import com.example.data.model.weather.domain.current.WeatherParameters
import com.example.data.model.weather.domain.current.WeatherResponse
import com.example.data.model.weather.domain.current.Wind
import com.example.data.model.weather.domain.forecast.City
import com.example.data.model.weather.domain.forecast.ForecastWeather
import com.example.data.model.weather.domain.forecast.ForecastWeatherResponse
import com.example.data.model.weather.domain.forecast.WeatherMain

fun toWeatherReport(
    weatherResponse: WeatherResponse,
    forecastWeatherResponse: ForecastWeatherResponse
): WeatherReport =
    WeatherReport(weatherResponse, forecastWeatherResponse)

fun toCurrentWeather(apiWeatherResponse: ApiWeatherResponse): WeatherResponse =
    with(apiWeatherResponse) {
        WeatherResponse(
            toWeatherDescription(apiWeatherDescriptions.firstOrNull()),
            toWeatherParameters(apiWeatherParameters),
            visibility,
            toWind(apiWind),
            cityName
        )
    }

fun toWind(apiWind: ApiWind): Wind =
    Wind(apiWind.speed, apiWind.degree)

fun toWeatherParameters(apiWeatherParameters: ApiWeatherParameters): WeatherParameters =
    with(apiWeatherParameters) {
        WeatherParameters(
            convertToCelsius(temperature),
            pressure,
            humidity,
            convertToCelsius(minTemperature),
            convertToCelsius(maxTemperature)
        )
    }

fun toWeatherDescription(apiWeatherDescription: ApiWeatherDescription?): WeatherDescription =
    WeatherDescription(
        apiWeatherDescription?.id ?: 0,
        apiWeatherDescription?.mainWeather ?: "",
        apiWeatherDescription?.description ?: ""
    )

fun toForecastWeather(apiForecastWeatherResponse: ApiForecastWeatherResponse): ForecastWeatherResponse =
    with(apiForecastWeatherResponse) {
        ForecastWeatherResponse(
            toCity(apiCity),
            toForecastData(apiForecastData)
        )
    }

fun toForecastData(apiForecastData: List<ApiForecastWeather>): List<ForecastWeather> =
    apiForecastData.map(::toApiForecastWeather).sortedByDescending { it.dayTime }.asReversed()

fun toApiForecastWeather(apiForecastWeather: ApiForecastWeather): ForecastWeather =
    with(apiForecastWeather) {
        ForecastWeather(
            dayTime,
            toWeatherMain(apiWeatherMain),
            apiWeatherDescriptions.map(::toWeatherDescription),
            dayTimeText
        )
    }

fun toWeatherMain(apiWeatherMain: ApiWeatherMain): WeatherMain =
    with(apiWeatherMain) {
        WeatherMain(
            convertToCelsius(temperature),
            convertToCelsius(minTemperature),
            convertToCelsius(maxTemperature),
            pressure,
            humidity
        )
    }

fun convertToCelsius(temperature: Double) = temperature - 273.15

fun toCity(apiCity: ApiCity): City =
    City(apiCity.id, apiCity.name, apiCity.country, apiCity.population)
