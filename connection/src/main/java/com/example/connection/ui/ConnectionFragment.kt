package com.example.connection.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.connection.R
import com.example.connection.di.CONNECTION_SCOPE
import com.example.connection.ui.ConnectionStatusViewModel.ConnectedStatusViewModel
import com.example.connection.ui.ConnectionStatusViewModel.DisconnectedStatusViewModel
import com.example.coreui.base.BaseFragment
import com.example.coreui.extension.collapseViewHeight
import com.example.coreui.extension.expandViewHeight
import com.example.coreui.extension.removeDrawables
import com.example.coreui.extension.setLeftDrawable
import kotlinx.android.synthetic.main.fragment_connectivity_bar.*

class ConnectionFragment : BaseFragment<ConnectionViewState>(), ConnectionContract.View {

    companion object {
        @LayoutRes
        val LAYOUT_RESOURCE = R.layout.fragment_connectivity_bar
    }

    private val presenter: ConnectionContract.Presenter by scopedInject()

    override fun getLayoutResource() = LAYOUT_RESOURCE
    override fun getScopeName() = CONNECTION_SCOPE
    override fun getViewPresenter() = presenter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        view.outlineProvider = null     // Removes elevation shadow
    }

    override fun render(viewState: ConnectionViewState) {
        when (val viewModel = viewState.viewModel) {
            is ConnectedStatusViewModel -> renderConnectedStatus(viewModel)
            is DisconnectedStatusViewModel -> renderDisconnectedStatus(viewModel)
            ConnectionStatusViewModel.EmptyConnectionStatusViewModel -> view?.collapseViewHeight()
            else -> throw IllegalArgumentException("$viewModel is not supported")
        }
    }

    private fun renderConnectedStatus(viewModel: ConnectedStatusViewModel) {
        connectivityBar_title.setText(viewModel.connectivityStatus)
        (connectivityBar_title.currentView as TextView).setLeftDrawable(viewModel.drawableStart)
        animateBackgroundChange(ContextCompat.getColor(context!!, viewModel.backgroundColor))
    }

    private fun renderDisconnectedStatus(viewModel: DisconnectedStatusViewModel) {
        connectivityBar_title.setCurrentText(viewModel.connectivityStatus)
        (connectivityBar_title.currentView as TextView).removeDrawables()

        view?.run {
            setBackgroundColor(ContextCompat.getColor(context!!, viewModel.backgroundColor))
            expandViewHeight()
        }
    }

    private fun animateBackgroundChange(newBackgroundColor: Int) {
        view?.background?.let {
            ValueAnimator.ofObject(ArgbEvaluator(), (it as ColorDrawable).color, newBackgroundColor).apply {
                addUpdateListener { animator ->
                    view?.setBackgroundColor(animator.animatedValue as Int)
                }
                start()
            }
        } ?: view?.setBackgroundColor(newBackgroundColor)
    }
}
