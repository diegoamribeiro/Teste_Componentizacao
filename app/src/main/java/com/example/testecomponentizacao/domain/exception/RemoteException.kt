package com.example.testecomponentizacao.domain.exception

import com.example.testecomponentizacao.domain.model.Product
import retrofit2.HttpException
import retrofit2.Response

sealed class RemoteException(message: Response<Any>) : HttpException(message){
    class Error(response: Response<*>) : HttpException(response)
    class Generic(response: String) :  Exception(response)
}
