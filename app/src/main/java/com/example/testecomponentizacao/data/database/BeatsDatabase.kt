package com.example.testecomponentizacao.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testecomponentizacao.domain.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = true
)
abstract class BeatsDatabase
: RoomDatabase(){

    abstract fun productDao() : ProductDAO

}