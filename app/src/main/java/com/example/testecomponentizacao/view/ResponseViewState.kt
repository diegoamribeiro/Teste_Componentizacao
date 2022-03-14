package com.example.testecomponentizacao.view

import com.example.testecomponentizacao.domain.model.Product

sealed class ResponseViewState<out T>(
    val data: T? = null,
    val throwable: Throwable? = null
)
 {
    class Success<T>(data: T): ResponseViewState<T>(data)
    class Error<T>(throwable: Throwable?, data: T? = null): ResponseViewState<T>(data, throwable)
    class Loading<T>: ResponseViewState<T>()
}