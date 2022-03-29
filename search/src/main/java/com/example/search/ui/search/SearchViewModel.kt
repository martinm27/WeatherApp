package com.example.search.ui.search

import com.example.search.ui.current.CurrentWeatherViewModel
import com.example.search.ui.forecast.ForecastWeatherViewModel

sealed class SearchViewModel {
    object EmptySearchViewModel : SearchViewModel()
    object ErrorSearchViewModel : SearchViewModel()
    data class CurrentSearchViewModel(
        val currentWeatherViewModel: CurrentWeatherViewModel,
        val forecastWeatherViewModels: List<ForecastWeatherViewModel>
    ) : SearchViewModel()
}
