package com.example.testecomponentizacao.viewmodel

import androidx.lifecycle.*
import com.example.testecomponentizacao.data.remote.NetworkResponse
import com.example.testecomponentizacao.model.Product
import com.example.testecomponentizacao.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    /** Database Call **/
    val readProducts: LiveData<List<Product>> = repository.local.readProducts().asLiveData()

    private fun insertProductsIntoDatabase(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertProducts(product)
        }
    }

    private fun offlineCacheProducts(products: List<Product>){
        for (i in products.indices){
            val item = products[i]
            insertProductsIntoDatabase(item)
        }
    }

    /** Remote Call **/
    val productResponse: MutableLiveData<NetworkResponse<List<Product>>> = MutableLiveData()

    fun getAllProduct() = viewModelScope.launch(Dispatchers.IO) {
        getAllSafeProducts()
    }

    private suspend fun getAllSafeProducts(){
        productResponse.postValue(NetworkResponse.Loading())
        val response = repository.remote.geAllProducts()
        productResponse.postValue(handleSafeProducts(response))
        offlineCacheProducts(response.body()!!)
    }

    private fun handleSafeProducts(response: Response<List<Product>>): NetworkResponse<List<Product>>{
        productResponse.postValue(NetworkResponse.Loading())
        return if (response.isSuccessful){
            val productResult = response.body()
            NetworkResponse.Success(productResult!!)
        }else{
            NetworkResponse.Error(response.message())
        }
    }

    fun searchFromDatabase(searchQuery: String): LiveData<List<Product>>{
        return repository.local.searchFromDatabase(searchQuery)
    }
}