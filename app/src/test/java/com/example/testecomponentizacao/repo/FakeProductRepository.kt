package com.example.testecomponentizacao.repo

import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.repo.ProductRepository

class FakeProductRepository : ProductRepository{

    private val products = mutableListOf<Product>()
    //private val productLiveData = MutableLiveData<List<Product>>(products)

    override suspend fun getProducts(): List<Product> {
        return products
    }

    override suspend fun insertProducts(listProducts: List<Product>) {
        products.addAll(listProducts)
    }

}