package com.example.testecomponentizacao.domain.repo

import com.example.testecomponentizacao.data.database.LocalDataSource
import com.example.testecomponentizacao.domain.model.User

class AuthRepositoryImpl(
    private val localDataSource: LocalDataSource
) : AuthRepository {

    override suspend fun registerUser(user: User) {
        localDataSource.registerUser(user)
    }

    override fun loginUser(username: String, password: String): User{
        return localDataSource.loginUser(username, password)
    }

    override suspend fun checkUser(username: String): User? {
        return localDataSource.checkUser(username)
    }

}