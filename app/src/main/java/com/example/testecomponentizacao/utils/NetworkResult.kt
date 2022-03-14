package com.example.testecomponentizacao.utils

sealed class NetworkResult<T>(
    open val data: T? = null,
    open val message: String? = null
) {

    class Loading<T> : NetworkResult<T>()
    data class Error<T>(override val message: String?, override val data: T?) : NetworkResult<T>(data, message)
    data class Success<T>(override val data: T?) : NetworkResult<T>(data)

}