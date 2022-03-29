package com.example.search.usecase

import com.example.core.usecase.QueryUseCase
import com.example.data.model.weather.WeatherReport
import com.example.data.repository.weather.WeatherRepository
import io.reactivex.Flowable

class QuerySearchResult(private val weatherRepository: WeatherRepository) :
    QueryUseCase<WeatherReport> {

    override fun invoke(): Flowable<WeatherReport> = weatherRepository.getWeatherReportForCity()
}
