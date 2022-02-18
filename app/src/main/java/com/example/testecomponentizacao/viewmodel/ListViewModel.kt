package com.example.testecomponentizacao.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecomponentizacao.data.remote.NetworkResponse
import com.example.testecomponentizacao.model.Product
import com.example.testecomponentizacao.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val productResponse: MutableLiveData<NetworkResponse<List<Product>>> = MutableLiveData()

    fun getAllProduct() = viewModelScope.launch(Dispatchers.IO) {
        getAllSafeProducts()
    }

    private suspend fun getAllSafeProducts(){
        productResponse.postValue(NetworkResponse.Loading())
        val response = repository.remote.geAllProducts()
        productResponse.postValue(handleSafeProducts(response))
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
}