package com.example.testecomponentizacao.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecomponentizacao.domain.usecase.GetProductsUseCase
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.view.ResponseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getProductsUseCase().onSuccess {products ->
            _productResponse.postValue(ResponseViewState.Success(products))
        }.onFailure {
            _productResponse.postValue(ResponseViewState.Error(it))
        }
    }

//    private suspend fun getAllSafeProducts(){
//        productResponse.postValue(ViewState.Loading())
//        val response = getProductsUseCase.geAllProducts()
//        productResponse.postValue(handleSafeProducts(response))
//        offlineCacheProducts(response.body()!!)
//    }
//


//    fun searchFromDatabase(searchQuery: String): LiveData<List<Product>>{
//        return repository.local.searchFromDatabase(searchQuery)
//    }
}