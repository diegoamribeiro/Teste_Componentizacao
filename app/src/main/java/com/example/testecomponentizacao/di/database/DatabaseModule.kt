package com.example.testecomponentizacao.di.database

import android.content.Context
import androidx.room.Room
import com.example.testecomponentizacao.data.database.ProductDAO
import com.example.testecomponentizacao.data.database.ProductDatabase
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

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesDao(database: ProductDatabase) : ProductDAO {
        return database.productDao()
    }

}