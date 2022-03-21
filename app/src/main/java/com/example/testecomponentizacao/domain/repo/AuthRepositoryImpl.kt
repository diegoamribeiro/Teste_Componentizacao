package com.example.testecomponentizacao.domain.repo

import androidx.lifecycle.LiveData
import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.domain.model.User

class AuthRepositoryImpl(
    private val localDataSource: LocalDataSource
) : AuthRepository {

    override suspend fun registerUser(user: User) : Long {
        return localDataSource.registerUser(user)
    }

    override fun loginUser(username: String, password: String): User{
        return localDataSource.loginUser(username, password)
    }

}