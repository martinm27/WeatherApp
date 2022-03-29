package com.example.connection.connection

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.processors.BehaviorProcessor

class ConnectionManagerImpl(
    backgroundScheduler: Scheduler,
    connectivityManager: ConnectivityManager
) : ConnectionManager {

    private val networkReadyPublisher: BehaviorProcessor<Boolean> =
        BehaviorProcessor.createDefault(false)
    private val isConnectionReadyFlowable =
        networkReadyPublisher
            .observeOn(backgroundScheduler)

    init {
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    networkReadyPublisher.onNext(true)
                }

                override fun onLost(network: Network) {
                    networkReadyPublisher.onNext(false)
                }
            }

        )
    }

    override fun isConnectionReady(): Flowable<Boolean> = isConnectionReadyFlowable

}
