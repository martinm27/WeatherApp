package com.example.coreui.base

import io.reactivex.Flowable

interface ViewPresenter<in View, ViewState> : Destroyable {

    fun start()

    fun viewAttached(view: View)

    /**
     * Publishes new view states on the main thread
     */
    fun viewState(): Flowable<ViewState>

    fun viewDetached()
}
