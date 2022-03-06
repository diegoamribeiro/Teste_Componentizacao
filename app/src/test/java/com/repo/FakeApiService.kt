package com.repo

import com.example.testecomponentizacao.data.remote.ProductApiService
import com.example.testecomponentizacao.model.Product
import retrofit2.Response

class FakeApiService : ProductApiService{
    override suspend fun getAllProducts(): Response<List<Product>> {
        TODO("Not yet implemented")
    }
}