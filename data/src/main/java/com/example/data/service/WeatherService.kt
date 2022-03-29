package com.example.data.service

import com.example.data.model.weather.api.current.ApiWeatherResponse
import com.example.data.model.weather.api.forecast.ApiForecastWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeatherForCity(@Query("q") cityName: String, @Query("appid") apiKey: String): Single<ApiWeatherResponse>

    @GET("forecast")
    fun getForecastWeatherForCity(@Query("q") cityName: String, @Query("appid") apiKey: String): Single<ApiForecastWeatherResponse>
}
