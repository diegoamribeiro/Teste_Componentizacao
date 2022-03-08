package com.example.testecomponentizacao.view

import com.example.testecomponentizacao.domain.model.Product

sealed class ViewState {
    object Loading: ViewState()
    data class Success(val product: Product): ViewState()
    data class Error(val errorType: Int): ViewState()
}