package com.example.testecomponentizacao.domain.usecase

import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.repo.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: AuthRepository
) {

    suspend fun registerUser(user: User): Long {
        return userRepository.registerUser(user)
    }

}