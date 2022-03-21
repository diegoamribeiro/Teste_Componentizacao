package com.example.testecomponentizacao.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testecomponentizacao.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(user: User) : Long

    @Query("SELECT * FROM user_table WHERE username LIKE :username AND password LIKE :password")
    fun loginUser(username: String, password: String) : User

}