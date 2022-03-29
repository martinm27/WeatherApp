package com.example.connection.resources

import android.content.res.Resources
import com.example.connection.R

class ConnectionStatusResourcesImpl(private val resources: Resources) :
    ConnectionStatusResources {

    override fun connectedLabelText(): String = resources.getString(R.string.connectivitybar_connectedStatus)

    override fun disconnectedLabelText(): String = resources.getString(R.string.connectivitybar_disconnectedStatus)

    override fun connectedDrawableRes() = R.drawable.ic_check_white

    override fun connectedLabelBackgroundColorRes(): Int = R.color.green

    override fun disconnectedLabelBackgroundColorRes(): Int = R.color.red
}
