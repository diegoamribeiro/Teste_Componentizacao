package com.example.testecomponentizacao.data.remote

import retrofit2.http.GET

interface ProductApiService {

    @GET("327c4367-8eca-4bd5-b98f-6aea396ea491")
    suspend fun getAllProducts(): List<ProductRemote>
}