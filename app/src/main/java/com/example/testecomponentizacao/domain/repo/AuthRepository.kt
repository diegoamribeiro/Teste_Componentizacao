package com.example.testecomponentizacao.domain.repo

import com.example.testecomponentizacao.domain.model.User

interface AuthRepository {

    suspend fun registerUser(user: User) : Long

    fun loginUser(username: String, password: String): User

}
