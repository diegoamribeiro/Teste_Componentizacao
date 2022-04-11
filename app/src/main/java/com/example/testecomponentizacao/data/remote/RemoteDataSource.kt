package com.example.testecomponentizacao.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Provides
import dagger.hilt.android.internal.Contexts
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productApiService: ProductApiService
) {

    suspend fun getAllProducts(): List<ProductRemote> {
        return productApiService.getAllProducts()
    }

}