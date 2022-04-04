package com.example.testecomponentizacao.domain.usecase


import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.repo.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: AuthRepository) {

    fun loginUser(username: String, password: String): User {
        return repository.loginUser(username, password)
    }
}