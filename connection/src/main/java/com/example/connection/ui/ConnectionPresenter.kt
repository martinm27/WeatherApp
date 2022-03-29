package com.example.connection.ui

import com.example.connection.resources.ConnectionStatusResources
import com.example.connection.ui.ConnectionStatus.*
import com.example.connection.ui.ConnectionStatusViewModel.*
import com.example.connection.usecase.QueryConnectionStatus
import com.example.coreui.base.BasePresenter
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

private const val CONNECTED_STATUS_SHOW_DURATION = 3000L

class ConnectionPresenter(
    private val queryConnectionStatus: QueryConnectionStatus,
    private val connectivityStatusResources: ConnectionStatusResources
) : BasePresenter<ConnectionContract.View, ConnectionViewState>(),
    ConnectionContract.Presenter {

    private val connectedStatus: ConnectedStatusViewModel by lazy {
        ConnectedStatusViewModel(
            connectivityStatusResources.connectedLabelText(),
            connectivityStatusResources.connectedLabelBackgroundColorRes(),
            connectivityStatusResources.connectedDrawableRes()
        )
    }

    private val disconnectedStatus by lazy {
        DisconnectedStatusViewModel(
            connectivityStatusResources.disconnectedLabelText(),
            connectivityStatusResources.disconnectedLabelBackgroundColorRes()
        )
    }

    override fun initialViewState(): ConnectionViewState =
        ConnectionViewState(EmptyConnectionStatusViewModel)

    override fun onStart() {
        query(queryConnectionStatus()
            .scan(INITIAL)
            { currentConnectionStatus, isNetworkReady ->
                determineConnectivityStatus(currentConnectionStatus, isNetworkReady)
            }
            .switchMap { if (it === CONNECTING) startTimer(it) else Flowable.just(it) }
            .map(this::toViewStateAction))
    }

    private fun determineConnectivityStatus(
        currentConnectionStatus: ConnectionStatus,
        isNetworkReady: Boolean
    ): ConnectionStatus =
        if (!isNetworkReady) {
            DISCONNECTED
        } else {
            when (currentConnectionStatus) {
                INITIAL -> INITIAL
                DISCONNECTED -> CONNECTING
                else -> CONNECTED
            }
        }

    private fun startTimer(connectingStatus: ConnectionStatus): Flowable<ConnectionStatus> =
        Flowable.timer(CONNECTED_STATUS_SHOW_DURATION, TimeUnit.MILLISECONDS, backgroundScheduler)
            .map { CONNECTED }
            .startWith(connectingStatus)

    private fun toViewStateAction(newConnectionStatus: ConnectionStatus) =
        { viewState: ConnectionViewState ->
            viewState.viewModel =
                when (newConnectionStatus) {
                    CONNECTING -> connectedStatus
                    DISCONNECTED -> disconnectedStatus
                    else -> EmptyConnectionStatusViewModel
                }
        }
}


enum class ConnectionStatus {
    INITIAL,
    CONNECTING,
    CONNECTED,
    DISCONNECTED
}
