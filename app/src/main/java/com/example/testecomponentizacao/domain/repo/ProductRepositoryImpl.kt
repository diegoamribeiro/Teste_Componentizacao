package com.example.testecomponentizacao.domain.repo


import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.data.remote.RemoteDataSource
import com.example.testecomponentizacao.data.remote.toProduct
import com.example.testecomponentizacao.domain.RemoteException
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.utils.hasInternetConnection
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val application: Application
) : ProductRepository {

    private lateinit var listProducts: MutableList<Product>

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getProducts(): List<Product> {
        try {
            return if (hasInternetConnection(application.applicationContext)){
                getRemoteProducts()
            }else{
                local.readProducts()
            }
        } catch (exception: HttpException) {
            when (exception.response()?.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> {
                    throw RemoteException.Error(exception.response()!!)
                }
                HttpURLConnection.HTTP_BAD_REQUEST -> {
                    throw RemoteException.Error(exception.response()!!)
                }
                else -> {
                    throw RemoteException.Generic(exception.response()!!.message())
                }
            }
        }
    }

    private suspend fun getRemoteProducts() : List<Product>{
        val list = remote.getAllProducts().map {
            it.toProduct()
        }
        val newList: MutableList<Product> = list.toMutableList()
        listProducts = newList
        insertProducts(listProducts)
        return listProducts
    }

    override suspend fun insertProducts(listProducts: List<Product>){
        local.insertProducts(this.listProducts)
    }

}