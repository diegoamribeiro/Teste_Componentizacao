package com.example.testecomponentizacao.resource

sealed class Resource<out T>(val status: Status, open val data: T?, open val message: String?) {

    class Success<T>(data: T?): Resource<T>(Status.SUCCESS, data, null)
    data class Error<T>(override val message: String?): Resource<T>(Status.ERROR,data = null, message = message)
    class Loading<T>(data: T?): Resource<T>(Status.LOADING, data, null)
}