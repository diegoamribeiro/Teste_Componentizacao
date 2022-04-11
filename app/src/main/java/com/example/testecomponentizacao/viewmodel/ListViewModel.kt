package com.example.testecomponentizacao.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.testecomponentizacao.domain.usecase.GetProductsUseCase
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.view.ResponseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    /** Remote Call **/
    private val _productResponse = MutableLiveData<ResponseViewState<List<Product>>>()
    val productResponse: LiveData<ResponseViewState<List<Product>>> = _productResponse

    fun getAllProducts() = viewModelScope.launch(Dispatchers.IO) {

        getProductsUseCase().onSuccess { products ->
            _productResponse.postValue(ResponseViewState.Success(products))
        }.onFailure {
            _productResponse.postValue(ResponseViewState.Error(it))
        }
    }

}