package com.example.testecomponentizacao.domain.exception

import java.lang.Exception

sealed class UserException(message: String) : Exception(message) {
    data class UserExistingException(override val message: String = "User already exists") : UserException(message)
    data class UserNotRegisteredException(override val message: String = "User not registered") : UserException(message)
    class UnknownException(message: String) : UserException(message)
}