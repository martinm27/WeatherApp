package com.example.search.di

import android.view.LayoutInflater
import com.example.core.di.BACKGROUND_SCHEDULER
import com.example.core.di.MAIN_SCHEDULER
import com.example.navigation.main.MainRouter
import com.example.search.ui.forecast.ForecastWeatherAdapter
import com.example.search.ui.search.SearchContract
import com.example.search.ui.search.SearchPresenter
import com.example.search.usecase.QuerySearchResult
import com.example.search.usecase.QueryYoutubeVideo
import com.example.search.usecase.SearchCityWeather
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SearchModule = module {
    scope(named(SEARCH_SCOPE)) {

        scoped<SearchContract.Presenter> {
            val router: MainRouter = it[0]
            SearchPresenter(router, get(), get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                start()
            }
        }
    }

    factory {
        val layoutInflater: LayoutInflater = it[0]
        ForecastWeatherAdapter(layoutInflater)
    }

    single { QuerySearchResult(get()) }
    single { SearchCityWeather(get()) }
    single { QueryYoutubeVideo(get()) }
}

const val SEARCH_SCOPE = "Search Scope"
