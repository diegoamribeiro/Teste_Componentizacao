package com.example.testecomponentizacao.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.model.User

@Database(
    entities = [Product::class, User::class],
    version = 2,
    exportSchema = true
)
abstract class BeatsDatabase
: RoomDatabase(){

    abstract fun productDao() : ProductDAO
    abstract fun userDao() : UserDao
}