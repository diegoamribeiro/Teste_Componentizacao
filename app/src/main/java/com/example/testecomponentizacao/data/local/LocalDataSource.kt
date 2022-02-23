package com.example.testecomponentizacao.data.local

import androidx.lifecycle.LiveData
import com.example.testecomponentizacao.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

    suspend fun insertProducts(product: Product){
        productDao.insertProduct(product)
    }

    fun readProducts(): Flow<List<Product>>{
        return productDao.readProduct()
    }

    fun searchFromDatabase(searchQuery: String): LiveData<List<Product>>{
        return productDao.searchDatabase(searchQuery)
    }

}