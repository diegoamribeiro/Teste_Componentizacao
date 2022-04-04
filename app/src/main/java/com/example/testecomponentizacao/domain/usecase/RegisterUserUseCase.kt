package com.example.testecomponentizacao.domain.usecase

import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.repo.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: AuthRepository
) {

    suspend fun registerUser(user: User) {
        userRepository.registerUser(user)
    }

    suspend fun checkUser(username: String): User?{
        return userRepository.checkUser(username)
    }

}