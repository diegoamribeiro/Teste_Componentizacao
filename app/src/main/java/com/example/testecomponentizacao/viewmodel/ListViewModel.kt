package com.example.testecomponentizacao.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecomponentizacao.domain.GetProductsUseCase
import com.example.testecomponentizacao.domain.model.Product
import com.example.testecomponentizacao.domain.RepositoryException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {


//    /** Database Call **/
//    val readProducts: LiveData<List<Product>> = repository.local.readProducts().asLiveData()
//
//    private fun insertProductsIntoDatabase(product: Product){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.insertProducts(product)
//        }
//    }
//
//    private fun offlineCacheProducts(products: List<Product>){
//        for (i in products.indices){
//            val item = products[i]
//            insertProductsIntoDatabase(item)
//        }
//    }

    /** Remote Call **/
    private val _productResponse = MutableLiveData<List<Product>>()
    val productResponse: LiveData<List<Product>> = _productResponse

    fun getAllProducts() = viewModelScope.launch(Dispatchers.IO) {
        getProductsUseCase().onSuccess {
            val products = it
            _productResponse.postValue(products)
        }.onFailure {
            throw RepositoryException.Generic()
        }
    }

//    private suspend fun getAllSafeProducts(){
//        productResponse.postValue(ViewState.Loading())
//        val response = getProductsUseCase.geAllProducts()
//        productResponse.postValue(handleSafeProducts(response))
//        offlineCacheProducts(response.body()!!)
//    }
//
//    private fun handleSafeProducts(response: Response<List<Product>>): ViewState<List<Product>> {
//        productResponse.postValue(ViewState.Loading())
//        return if (response.isSuccessful){
//            val productResult = response.body()
//            ViewState.Success(productResult!!)
//        }else{
//            ViewState.Error(response.message())
//        }
//    }

//    fun searchFromDatabase(searchQuery: String): LiveData<List<Product>>{
//        return repository.local.searchFromDatabase(searchQuery)
//    }
}