package com.example.testecomponentizacao.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecomponentizacao.data.preferences.UserPreferencesRepository
import com.example.testecomponentizacao.domain.exception.UserException
import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.domain.usecase.LoginUserUseCase
import com.example.testecomponentizacao.domain.usecase.RegisterUserUseCase
import com.example.testecomponentizacao.resource.Resource
import com.example.testecomponentizacao.utils.FingerPrintHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val preferencesRepository: UserPreferencesRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<User>>()
    val loginResponse: LiveData<Resource<User>> = _loginResponse

    private val _registerResponse = MutableLiveData<Resource<Unit>>()
    val registerResponse: LiveData<Resource<Unit>> = _registerResponse

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser

    val username = preferencesRepository.userNameFlow
    val password = preferencesRepository.passwordFlow

    private fun isAuthenticationAvailable() {
        viewModelScope.launch {
            if (FingerPrintHelper.isAuthenticationAvailable(context) && !logged()) {
                _loggedUser.value = true
            }
        }
    }

    suspend fun logged(): Boolean {
        return (username.first().isEmpty() && password.first().isEmpty())
    }

    init {
        registerResponse
        isAuthenticationAvailable()
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
                    is UserException.UserExistingException -> {
                        throw exception
                    }
                    is UserException.UnknownException -> {
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