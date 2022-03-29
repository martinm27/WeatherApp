package com.example.connection.usecase

import com.example.connection.connection.ConnectionManager
import com.example.core.usecase.QueryUseCase
import io.reactivex.Flowable

class QueryConnectionStatus(private val connectionManager: ConnectionManager) :
    QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = connectionManager.isConnectionReady()
        .distinctUntilChanged()
}
