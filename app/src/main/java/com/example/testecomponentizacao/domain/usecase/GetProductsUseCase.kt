package com.example.testecomponentizacao.domain.usecase

import com.example.testecomponentizacao.domain.exception.RemoteException
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.repo.ProductRepository
import dagger.Provides
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke() : Result<List<Product>> {
        return try {
            Result.success(repository.getProducts())
        }catch (exception: RemoteException){
            Result.failure(exception)
        }
    }
}