package com.example.testecomponentizacao.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository
import com.example.testecomponentizacao.domain.exception.UserException
import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.usecase.LoginUserUseCase
import com.example.testecomponentizacao.domain.usecase.RegisterUserUseCase
import com.example.testecomponentizacao.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<User>>()
    val loginResponse: LiveData<Resource<User>> = _loginResponse

    private val _registerResponse = MutableLiveData<Resource<Unit>>()
    val registerResponse: LiveData<Resource<Unit>> = _registerResponse

    private val _authentication = MutableLiveData<List<String>>()
    val authentication: LiveData<List<String>> = _authentication
    val username = preferencesRepository.userNameFlow.asLiveData()
    val password = preferencesRepository.passwordFlow.asLiveData()



    init {
        registerResponse
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val check: String? = registerUserUseCase.checkUser(user.username)?.username
                if (check != null) {
                    _registerResponse.postValue(Resource.Error(message = UserException.UserExistingException().message))
                } else {
                    val data = registerUserUseCase.registerUser(user)
                    _registerResponse.postValue(Resource.Success(data))
                }
            } catch (exception: UserException) {
                when (exception) {
//                    is UserException.UserExistingException -> {
//                        throw exception
//                    }
                    is UserException.UnknownException -> {
                        Log.d("***Throw", exception.message!!)
                        throw exception
                    }
                    else -> {}
                }
            }
        }
    }


    fun loginUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginResponse.postValue(Resource.Loading(null))
            try {
                val data = loginUserUseCase.loginUser(username, password)
                _loginResponse.postValue(Resource.Success(data))
                preferencesRepository.saveUserData(username, password)
            } catch (exception: Exception) {
                _loginResponse.postValue(Resource.Error(exception.message!!))
            }
        }
    }
}