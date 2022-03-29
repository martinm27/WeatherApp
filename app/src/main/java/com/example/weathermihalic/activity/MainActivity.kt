package com.example.weathermihalic.activity

import android.os.Bundle
import com.example.coreui.base.BaseActivity
import com.example.navigation.main.MainRouter
import com.example.weathermihalic.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

private const val LAYOUT_RESOURCE = R.layout.activity_main

class MainActivity : BaseActivity() {

    private val router: MainRouter by inject(parameters = { parametersOf(this) })

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.showMainScreen()
    }
}
