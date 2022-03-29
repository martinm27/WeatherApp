package com.example.connection.resources

interface ConnectionStatusResources {

    fun connectedLabelText() : String

    fun disconnectedLabelText() : String

    fun connectedDrawableRes() : Int

    fun connectedLabelBackgroundColorRes() : Int

    fun disconnectedLabelBackgroundColorRes() : Int
}
