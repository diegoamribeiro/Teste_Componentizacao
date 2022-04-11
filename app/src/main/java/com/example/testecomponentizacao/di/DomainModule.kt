package com.example.testecomponentizacao.di

import android.app.Application
import android.content.Context
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.data.remote.RemoteDataSource
import com.example.testecomponentizacao.domain.repo.ProductRepository
import com.example.testecomponentizacao.domain.repo.ProductRepositoryImpl
import com.example.testecomponentizacao.domain.repo.AuthRepository
import com.example.testecomponentizacao.domain.repo.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {


    @Provides
    @Singleton
    fun providesProductRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): ProductRepository{
        return ProductRepositoryImpl(localDataSource, remoteDataSource, context)
    }

    @Provides
    @Singleton
    fun providesUserRepository(localDataSource: LocalDataSource) : AuthRepository {
        return AuthRepositoryImpl(localDataSource)
    }

}