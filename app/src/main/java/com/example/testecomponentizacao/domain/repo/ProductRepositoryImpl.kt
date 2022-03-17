package com.example.testecomponentizacao.domain.repo


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.data.remote.RemoteDataSource
import com.example.testecomponentizacao.data.remote.toProduct
import com.example.testecomponentizacao.domain.RemoteException
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.utils.Utils.hasInternetConnection
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private var context: Context
) : ProductRepository {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getProducts(): List<Product> {
        try {
            return if (!hasInternetConnection(context)){
                local.readProducts()
            }else{
                getRemoteProducts()
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

    private suspend fun getRemoteProducts(): List<Product> {
        val list = remote.getAllProducts().map {
            it.toProduct()
        }
        if (list != local.readProducts()){
            deleteAllProducts()
            insertProducts(list)
        }
        return list
    }

    override suspend fun insertProducts(products: List<Product>){
        local.insertProducts(products)
    }

    override suspend fun deleteAllProducts() {
        local.deleteAllProducts()
    }

}