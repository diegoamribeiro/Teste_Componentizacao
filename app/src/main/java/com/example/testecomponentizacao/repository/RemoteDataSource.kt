package com.example.testecomponentizacao.repository

import com.example.testecomponentizacao.data.remote.ProductApiService
import com.example.testecomponentizacao.model.Product
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productApiService: ProductApiService
) {

    suspend fun geAllProducts(): Response<List<Product>>{
        return productApiService.getAllProducts()
    }

}