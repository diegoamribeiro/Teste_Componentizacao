package com.example.testecomponentizacao.domain

import com.example.testecomponentizacao.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() : Result<List<Product>> {
        return try {
            Result.success(repository.getProducts())
        }catch (exception: RepositoryException){
            Result.failure(exception)
        }
    }
}