package com.example.testecomponentizacao.data.database

import com.example.testecomponentizacao.domain.model.Product
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDAO: ProductDAO
) {

    fun readProducts(): List<Product>{
        return productDAO.readProducts()
    }

    suspend fun insertProducts(product: List<Product>){
        productDAO.insertProduct(product)
    }

}