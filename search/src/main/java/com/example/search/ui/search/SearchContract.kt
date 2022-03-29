package com.example.search.ui.search

import com.example.coreui.base.BaseView
import com.example.coreui.base.ViewPresenter

interface SearchContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, SearchViewState> {

        fun searchCity(searchQuery: String)

        fun watchOnYoutube(query: String)
    }
}
