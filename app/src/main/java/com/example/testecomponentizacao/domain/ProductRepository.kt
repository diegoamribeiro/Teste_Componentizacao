package com.example.testecomponentizacao.domain

import com.example.testecomponentizacao.domain.model.Product


interface ProductRepository {
    suspend fun getProducts(): List<Product>
}