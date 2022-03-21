package com.example.testecomponentizacao.data.database

import androidx.lifecycle.LiveData
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.model.User
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDAO: ProductDAO,
    private val userDao: UserDao
) {

    fun readProducts(): List<Product>{
        return productDAO.readProducts()
    }

    suspend fun insertProducts(products: List<Product>){
        productDAO.insertProduct(products)
    }

    suspend fun deleteAllProducts(){
        productDAO.deleteAllProducts()
    }

    suspend fun registerUser(user: User) : Long {
        return userDao.registerUser(user)
    }

    fun loginUser(username: String, password: String) : User{
        return userDao.loginUser(username, password)
    }

}