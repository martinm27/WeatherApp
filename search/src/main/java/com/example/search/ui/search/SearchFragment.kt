package com.example.search.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.coreui.base.BaseFragment
import com.example.coreui.extension.collapseViewHeight
import com.example.coreui.extension.expandViewHeight
import com.example.coreui.extension.fadeIn
import com.example.coreui.extension.fadeOut
import com.example.coreui.view.VerticalItemDecoration
import com.example.navigation.main.MainRouter
import com.example.search.R
import com.example.search.di.SEARCH_SCOPE
import com.example.search.ui.forecast.ForecastWeatherAdapter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.current_weather_layout.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_bar.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment<SearchViewState>(),
    SearchContract.View {

    companion object {
        @LayoutRes
        val LAYOUT_RESOURCE = R.layout.fragment_search

        const val TAG = "SearchFragment"

        fun newInstance() = SearchFragment()
    }

    private val router: MainRouter by inject(parameters = { parametersOf(activity) })
    private val presenter: SearchContract.Presenter by scopedInject(parameters = { parametersOf(router) })
    private val forecastWeatherAdapter: ForecastWeatherAdapter by inject(parameters = {
        parametersOf(
            layoutInflater
        )
    })

    override fun getLayoutResource() = LAYOUT_RESOURCE
    override fun getScopeName() = SEARCH_SCOPE
    override fun getViewPresenter() = presenter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    subscriber.onNext(query)
                    return false
                }
            })
        })
            .subscribe { text -> presenter.searchCity(text) }

        toolbarClose.setOnClickListener {
            weather_main_content.fadeOut()
            search_bar_layout.expandViewHeight()
            (search_view as SearchView).setQuery("", false)
        }

        watch_on_youtube_button.setOnClickListener {
            presenter.watchOnYoutube(buildQuery())
        }

        initForecastRecycler()
    }

    private fun buildQuery(): String =
        listOf(current_weather_main_subtitle.text, toolbarTitle.text).joinToString(" ")


    private fun initForecastRecycler() {
        LinearSnapHelper().attachToRecyclerView(forecast_five_days_recycler)
        forecast_five_days_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        forecast_five_days_recycler.adapter = forecastWeatherAdapter

        forecast_five_days_recycler.addItemDecoration(get<VerticalItemDecoration>(parameters = {
            parametersOf(
                context!!
            )
        }))
    }

    override fun render(viewState: SearchViewState) {
        when (viewState.viewModel) {
            is SearchViewModel.EmptySearchViewModel -> showSearch()
            is SearchViewModel.ErrorSearchViewModel -> showErrorState()
            is SearchViewModel.CurrentSearchViewModel -> showWeatherReport(viewState.viewModel as SearchViewModel.CurrentSearchViewModel)
        }
    }

    private fun showWeatherReport(searchViewModel: SearchViewModel.CurrentSearchViewModel) {
        search_bar_layout.collapseViewHeight()
        with(searchViewModel.currentWeatherViewModel) {
            toolbarTitle.text = cityName

            current_weather_main_title.text = mainWeather
            current_weather_main_subtitle.text = mainWeatherDescription
            current_weather_icon.setImageResource(weatherIcon)

            current_weather_main_temperature.text = temperature
            current_weather_main_max_temperature.text = maxTemperature
            current_weather_main_min_temperature.text = minTemperature
            current_weather_main_pressure.text = pressure
            current_weather_main_humidity.text = humidity
        }

        forecastWeatherAdapter.submitList(searchViewModel.forecastWeatherViewModels)
        weather_main_content.fadeIn()
    }

    private fun showErrorState() {
        Toast.makeText(
            context,
            "Error happened during search. Check your input or try again later...",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showSearch() {
        search_bar_layout.expandViewHeight()
        weather_main_content.fadeOut()
    }
}
