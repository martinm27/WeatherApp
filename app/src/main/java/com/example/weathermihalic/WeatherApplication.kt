package com.example.weathermihalic

import android.app.Application
import com.example.connection.di.ConnectionModule
import com.example.core.di.ThreadingModule
import com.example.data.di.DataModule
import com.example.search.di.SearchModule
import com.example.weathermihalic.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(coreModules + libModules + featureModules)
        }
    }

    private val coreModules = listOf(
        AppModule,
        ThreadingModule
    )

    private val libModules = listOf(DataModule)

    private val featureModules = listOf(
        SearchModule,
        ConnectionModule
    )
}
