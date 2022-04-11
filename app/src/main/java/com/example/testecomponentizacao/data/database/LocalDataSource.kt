package com.example.testecomponentizacao.data.database

import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.model.User
import dagger.Provides
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDAO: ProductDAO,
    private val userDao: UserDao
) {

    suspend fun readProducts(): List<Product>{
        return productDAO.readProducts()
    }

    suspend fun insertProducts(products: List<Product>){
        productDAO.insertProduct(products)
    }

    suspend fun deleteAllProducts(){
        productDAO.deleteAllProducts()
    }

    suspend fun registerUser(user: User) {
        return userDao.registerUser(user)
    }

    fun loginUser(username: String, password: String) : User{
        return userDao.loginUser(username, password)
    }

    suspend fun checkUser(username: String) : User?{
        return userDao.checkUsers(username)
    }

}