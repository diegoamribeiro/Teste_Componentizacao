package com.example.testecomponentizacao.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productApiService: ProductApiService
) {

    suspend fun getAllProducts(): List<ProductRemote> {
        return productApiService.getAllProducts()
    }

}