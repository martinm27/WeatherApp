package com.example.search.ui.search

import com.example.coreui.base.BasePresenter
import com.example.data.model.weather.domain.forecast.ForecastWeatherResponse
import com.example.navigation.main.MainRouter
import com.example.search.ui.forecast.ForecastWeatherViewModel
import com.example.search.ui.search.SearchViewModel.*
import com.example.search.ui.toCurrentWeatherViewModel
import com.example.search.ui.toForecastWeatherViewModel
import com.example.search.usecase.QuerySearchResult
import com.example.search.usecase.QueryYoutubeVideo
import com.example.search.usecase.SearchCityWeather
import io.reactivex.disposables.Disposables

private const val MIDNIGHT = "00:00:00"
private const val NOON = "12:00:00"

class SearchPresenter(
    private val mainRouter: MainRouter,
    private val searchCityWeather: SearchCityWeather,
    private val querySearchResult: QuerySearchResult,
    private val queryYoutubeVideo: QueryYoutubeVideo
) : BasePresenter<SearchContract.View, SearchViewState>(),
    SearchContract.Presenter {

    private var youtubeVideoDisposable = Disposables.disposed()

    override fun initialViewState(): SearchViewState =
        SearchViewState(EmptySearchViewModel)

    override fun onStart() {
        query(querySearchResult()
            .map { weatherReport ->
                { viewState: SearchViewState ->
                    viewState.viewModel = if (weatherReport.isValid())
                        CurrentSearchViewModel(
                            toCurrentWeatherViewModel(weatherReport.currentWeather),
                            toForecastWeatherViewModels(weatherReport.forecastWeather)
                        )
                    else ErrorSearchViewModel
                }
            }
        )
    }

    private fun toForecastWeatherViewModels(forecastWeather: ForecastWeatherResponse?): List<ForecastWeatherViewModel> =
        forecastWeather?.forecastData?.filter {
            it.dayTimeText.contains(MIDNIGHT) || it.dayTimeText.contains(NOON)
        }
            ?.map(::toForecastWeatherViewModel) ?: emptyList()

    override fun searchCity(searchQuery: String) = runCommand(searchCityWeather(searchQuery))

    override fun watchOnYoutube(query: String) {
        youtubeVideoDisposable = queryYoutubeVideo(query)
            .distinctUntilChanged()
            .observeOn(mainThreadScheduler)
            .subscribeOn(backgroundScheduler)
            .subscribe({
                mainRouter.showYoutube(it.query)
            }, {
                mutateViewState { it.viewModel = ErrorSearchViewModel }
            })
    }

    override fun onDestroy() {
        youtubeVideoDisposable.dispose()
        super.onDestroy()
    }

}

