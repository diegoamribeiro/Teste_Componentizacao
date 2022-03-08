package com.example.testecomponentizacao.domain


import com.example.testecomponentizacao.data.remote.ProductApiService
import com.example.testecomponentizacao.data.remote.toProduct
import com.example.testecomponentizacao.domain.model.Product
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
    ) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        try {
            return productApiService.getAllProducts().map { product ->
                product.toProduct()
            }

        }catch (exception: HttpException){
            when(exception.code()){
                HttpURLConnection.HTTP_NOT_FOUND -> {
                    throw RepositoryException.NotFound()
                }
                else -> {
                    throw RepositoryException.Generic()
                }
            }
        }catch (exception: Exception){
            throw RepositoryException.Generic()
        }
    }
}