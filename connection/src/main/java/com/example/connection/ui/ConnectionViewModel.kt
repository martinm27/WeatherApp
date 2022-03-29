package com.example.connection.ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

sealed class ConnectionStatusViewModel {
    object EmptyConnectionStatusViewModel : ConnectionStatusViewModel()
    data class DisconnectedStatusViewModel(val connectivityStatus: String, @ColorRes val backgroundColor: Int) : ConnectionStatusViewModel()
    data class ConnectedStatusViewModel(val connectivityStatus: String, @ColorRes val backgroundColor: Int, @DrawableRes val drawableStart: Int) : ConnectionStatusViewModel()
}
