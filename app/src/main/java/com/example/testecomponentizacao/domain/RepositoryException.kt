package com.example.testecomponentizacao.domain

sealed class RepositoryException(message: String) : Exception(message){
    class NotFound : RepositoryException("Not Found")
    class Generic :  RepositoryException("Unknown Error")
}
