package com.example.testecomponentizacao.di

import android.content.Context
import androidx.room.Room
import com.example.testecomponentizacao.data.local.ProductDatabase
import com.example.testecomponentizacao.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(database: ProductDatabase) = database.productDao()
}
