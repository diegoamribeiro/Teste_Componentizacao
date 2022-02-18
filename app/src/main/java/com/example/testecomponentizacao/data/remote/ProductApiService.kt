package com.example.testecomponentizacao.data.remote

import com.example.testecomponentizacao.model.Product
import com.example.testecomponentizacao.utils.Constants.PRODUCT_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET(PRODUCT_ENDPOINT)
    suspend fun getAllProducts(): Response<List<Product>>
}