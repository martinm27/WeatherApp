package com.example.core.usecase

import io.reactivex.Completable

interface CommandUseCase {

    operator fun invoke(): Completable
}
