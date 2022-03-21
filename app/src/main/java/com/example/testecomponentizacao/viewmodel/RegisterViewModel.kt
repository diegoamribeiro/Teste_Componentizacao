package com.example.testecomponentizacao.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.usecase.LoginUserUseCase
import com.example.testecomponentizacao.domain.usecase.RegisterUserUseCase
import com.example.testecomponentizacao.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<User>>()
    val loginResponse : LiveData<Resource<User>> = _loginResponse

    private val _registerResponse = MutableLiveData<Resource<Long>>()
    val registerResponse: LiveData<Resource<Long>> = _registerResponse

    init {
        registerResponse
    }


    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            _registerResponse.postValue(Resource.loading(null))
            try {
                val data = registerUserUseCase.registerUser(user)
                _registerResponse.postValue(Resource.success(data))
            } catch (exception: Exception) {
                _registerResponse.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

    fun loginUser(username: String, password: String){

        viewModelScope.launch(Dispatchers.IO) {
            _loginResponse.postValue(Resource.loading(null))
            try {
                val data = loginUserUseCase.loginUser(username, password)
                _loginResponse.postValue(Resource.success(data))
            }catch (exception: Exception){
                _loginResponse.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

}