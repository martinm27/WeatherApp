package com.example.connection.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.connection.connection.ConnectionManager
import com.example.connection.connection.ConnectionManagerImpl
import com.example.connection.resources.ConnectionStatusResources
import com.example.connection.resources.ConnectionStatusResourcesImpl
import com.example.connection.ui.ConnectionContract
import com.example.connection.ui.ConnectionPresenter
import com.example.connection.usecase.QueryConnectionStatus
import com.example.core.di.BACKGROUND_SCHEDULER
import com.example.core.di.MAIN_SCHEDULER
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ConnectionModule = module {

    scope(named(CONNECTION_SCOPE)) {

        scoped<ConnectionContract.Presenter> {
            ConnectionPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                start()
            }
        }
    }

    single<ConnectionStatusResources> { ConnectionStatusResourcesImpl(get()) }
    single { QueryConnectionStatus(get()) }
    single<ConnectionManager> {
        ConnectionManagerImpl(
            get(named(BACKGROUND_SCHEDULER)),
            androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }
}

const val CONNECTION_SCOPE = "Connection Scope"
