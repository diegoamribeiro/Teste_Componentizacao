package com.example.testecomponentizacao.di

import com.example.testecomponentizacao.domain.ProductRepository
import com.example.testecomponentizacao.domain.ProductRepositoryImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsProductsRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

}