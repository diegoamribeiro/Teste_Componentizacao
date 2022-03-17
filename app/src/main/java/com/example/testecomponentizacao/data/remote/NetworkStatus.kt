package com.example.testecomponentizacao.data.remote

sealed class NetworkStatus{
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
