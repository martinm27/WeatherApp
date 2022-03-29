package com.example.search.usecase

import com.example.core.usecase.CommandUseCaseWithParam
import com.example.data.repository.weather.WeatherRepository
import io.reactivex.Completable

class SearchCityWeather(private val weatherRepository: WeatherRepository) :
    CommandUseCaseWithParam<String> {

    override fun invoke(param: String): Completable =
        Completable.fromAction { weatherRepository.searchWeatherForCity(param) }
}
