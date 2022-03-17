package com.example.testecomponentizacao.domain.repo

import com.example.testecomponentizacao.domain.model.Product


interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun insertProducts(products: List<Product>)
    suspend fun deleteAllProducts()
}