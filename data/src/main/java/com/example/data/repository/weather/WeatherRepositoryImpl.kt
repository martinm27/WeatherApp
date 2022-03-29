package com.example.data.repository.weather

import android.annotation.SuppressLint
import com.example.data.mapper.toCurrentWeather
import com.example.data.mapper.toForecastWeather
import com.example.data.mapper.toWeatherReport
import com.example.data.model.weather.WeatherReport
import com.example.data.service.WeatherService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.processors.PublishProcessor
import timber.log.Timber

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val backgroundScheduler: Scheduler
) : WeatherRepository {

    private var currentWeatherSearch: PublishProcessor<WeatherReport> = PublishProcessor.create()

    @SuppressLint("CheckResult")
    override fun searchWeatherForCity(cityName: String) {
        Single.zip(
            weatherService.getCurrentWeatherForCity(cityName,
                API_KEY
            )
                .map(::toCurrentWeather),
            weatherService.getForecastWeatherForCity(cityName,
                API_KEY
            )
                .map(::toForecastWeather),
            BiFunction(::toWeatherReport)
        )
            .subscribeOn(backgroundScheduler)
            .observeOn(backgroundScheduler)
            .subscribe({ currentWeatherSearch.onNext(it) },
                {
                currentWeatherSearch.onNext(WeatherReport.EMPTY)
                Timber.w(it)
            })
    }

    override fun getWeatherReportForCity(): Flowable<WeatherReport> = currentWeatherSearch
}
