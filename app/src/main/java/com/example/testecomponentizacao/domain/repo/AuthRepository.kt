package com.example.testecomponentizacao.domain.repo

import com.example.testecomponentizacao.domain.model.User

interface AuthRepository {

    suspend fun registerUser(user: User)

    fun loginUser(username: String, password: String): User

    suspend fun checkUser(username: String) : User?

}
