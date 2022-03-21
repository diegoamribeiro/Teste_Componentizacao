package com.example.testecomponentizacao.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.RegisterActivity
import com.example.testecomponentizacao.data.remote.NetworkStatus
import com.example.testecomponentizacao.data.remote.NetworkStatusHelper
import com.example.testecomponentizacao.databinding.ActivityLoginBinding
import com.example.testecomponentizacao.resource.Status
import com.example.testecomponentizacao.utils.Utils.hideKeyboard
import com.example.testecomponentizacao.utils.Utils.hideStatusBar
import com.example.testecomponentizacao.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        hideStatusBar(window)
        hideKeyboard(this)

        binding.activityLoginButtonLogin.setOnClickListener {
            val intentHomeLogged = Intent(this, HomeLoggedActivity::class.java)
            startActivity(intentHomeLogged)
        }

        binding.activityLoginSigninTxtLink.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }
        binding.activityLoginButtonLogin.setOnClickListener {
            loginUser()
        }
        //checkInternetConnection()
        setContentView(view)
    }


    private fun loginUser() {
        val username = binding.activityLoginEdtUsername.text.toString()
        val password = binding.activityLoginEdtPassword.text.toString()

        val validFields = validateFields(username, password)

        if (validFields){
            viewModel.loginUser(username, password)
            viewModel.loginResponse.observe(this){
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            val intent = Intent(this, HomeLoggedActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Usuário inválido", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    else -> {

                    }
                }
            }
        }else{
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateFields(username: String, password: String): Boolean {
        return username.isNotBlank() && password.isNotBlank()
    }
}

