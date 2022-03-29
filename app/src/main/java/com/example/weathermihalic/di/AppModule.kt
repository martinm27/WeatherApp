package com.example.weathermihalic.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.coreui.view.VerticalItemDecoration
import com.example.navigation.main.MainRouter
import com.example.weathermihalic.R
import com.example.weathermihalic.routing.MainRouterImpl
import org.koin.dsl.module

val AppModule = module {

    factory<MainRouter> {
        val activity: FragmentActivity = it[0]
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        MainRouterImpl(activity, fragmentManager)
    }

    factory {
        val context: Context = it[0]
        val dimenResource = R.dimen.common_edge_margin
        val itemOffset: Int = context.resources.getDimension(dimenResource).toInt()
        VerticalItemDecoration(itemOffset)
    }

    single<Resources> { get<Application>().resources }
}

