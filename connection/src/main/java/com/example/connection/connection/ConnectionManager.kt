package com.example.connection.connection

import io.reactivex.Flowable

interface ConnectionManager {

    fun isConnectionReady(): Flowable<Boolean>
}
