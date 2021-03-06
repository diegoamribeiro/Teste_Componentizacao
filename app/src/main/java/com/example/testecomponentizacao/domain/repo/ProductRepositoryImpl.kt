package com.example.testecomponentizacao.domain.repo

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.data.remote.RemoteDataSource
import com.example.testecomponentizacao.data.remote.toProduct
import com.example.testecomponentizacao.domain.exception.RemoteException
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.utils.Utils
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    @ApplicationContext private val context: Context
) : ProductRepository {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getProducts(): List<Product> {
        try {
            return if (Utils.hasInternetConnection(context)){
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


//    @RequiresApi(Build.VERSION_CODES.M)
//    fun hasInternetConnection(): Boolean {
//
//        val connectivityManager = context.getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//        connectivityManager.addDefaultNetworkActiveListener {
//
//        }
//        val activeNetwork = connectivityManager.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//        return when {
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> return false
//        }
//    }

}