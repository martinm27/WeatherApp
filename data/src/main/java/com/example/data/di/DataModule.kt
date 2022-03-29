package com.example.data.di

import com.example.core.di.BACKGROUND_SCHEDULER
import com.example.data.repository.weather.WeatherRepository
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.data.repository.youtube.YoutubeRepository
import com.example.data.repository.youtube.YoutubeRepositoryImpl
import com.example.data.service.WeatherService
import com.example.data.service.YoutubeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val WEATHER_SERVER_URL = "https://api.openweathermap.org/data/2.5/"
private const val YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/"

val DataModule = module {

    single { createRestService<WeatherService>(createOkHttpClient(), WEATHER_SERVER_URL) }
    single { createRestService<YoutubeService>(createOkHttpClient(), YOUTUBE_API_URL) }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            get(),
            get(named(BACKGROUND_SCHEDULER))
        )
    }
    single<YoutubeRepository> { YoutubeRepositoryImpl(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

inline fun <reified T> createRestService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
