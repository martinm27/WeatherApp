package com.example.search.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coreui.util.DiffUtilCallback
import com.example.search.R
import com.example.search.ui.forecast.ForecastWeatherAdapter.ForecastWeatherViewHolder
import kotlinx.android.synthetic.main.forecast_weather_item.view.*

class ForecastWeatherAdapter(
    private val layoutInflater: LayoutInflater
) : ListAdapter<ForecastWeatherViewModel, ForecastWeatherViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastWeatherViewHolder(
            layoutInflater.inflate(
                ForecastWeatherViewHolder.LAYOUT,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    class ForecastWeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            val LAYOUT = R.layout.forecast_weather_item
        }

        private val dayTimeTitle by lazy { itemView.forecast_status_days_date }
        private val mainWeatherText by lazy { itemView.forecast_weather_main_title }
        private val mainWeatherDescriptionText by lazy { itemView.forecast_weather_main_subtitle }
        private val weatherIconImage: ImageView by lazy { itemView.forecast_weather_icon }

        private val temperatureText by lazy { itemView.forecast_weather_main_temperature }
        private val minTemperatureText by lazy { itemView.forecast_weather_main_min_temperature }
        private val maxTemperatureText by lazy { itemView.forecast_weather_main_max_temperature }

        private val pressureText by lazy { itemView.forecast_weather_main_pressure }
        private val humidityText by lazy { itemView.forecast_weather_main_humidity }

        fun render(forecastWeatherViewModel: ForecastWeatherViewModel) {
            with(forecastWeatherViewModel) {
                dayTimeTitle.text = dayTime
                mainWeatherText.text = mainWeather
                mainWeatherDescriptionText.text = mainWeatherDescription
                weatherIconImage.setImageResource(weatherIcon)
                temperatureText.text = temperature
                minTemperatureText.text = minTemperature
                maxTemperatureText.text = maxTemperature
                pressureText.text = pressure
                humidityText.text = humidity
            }
        }
    }
}
