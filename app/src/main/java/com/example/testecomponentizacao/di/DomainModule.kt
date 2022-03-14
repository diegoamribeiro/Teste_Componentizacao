package com.example.testecomponentizacao.di

import com.example.testecomponentizacao.domain.repo.ProductRepository
import com.example.testecomponentizacao.domain.repo.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsProductsRepository(
        productRepositoryImpl: ProductRepositoryImpl,
    ): ProductRepository

}