package com.example.connection.ui

import com.example.coreui.base.BaseView
import com.example.coreui.base.ViewPresenter

interface ConnectionContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, ConnectionViewState>
}
