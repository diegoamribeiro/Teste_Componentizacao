package com.example.testecomponentizacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.testecomponentizacao.databinding.ActivityRegisterBinding
import com.example.testecomponentizacao.domain.model.User
import com.example.testecomponentizacao.resource.Status
import com.example.testecomponentizacao.utils.Utils.hideKeyboard
import com.example.testecomponentizacao.utils.Utils.hideStatusBar
import com.example.testecomponentizacao.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        supportActionBar?.hide()
        hideStatusBar(window)
        hideKeyboard(this)
        binding.activityRegisterLoginTxtLink.setOnClickListener {
            finish()
        }

        binding.activityRegisterButtonLogin.setOnClickListener {
            registerUser()
        }

        setContentView(view)
    }

    private fun registerUser() {

        val name = binding.activityRegisterEdtName.text.toString()
        val username = binding.activityRegisterEdtUsername.text.toString()
        val password = binding.activityRegisterEdtPassword.text.toString()

        val validateFields = validateFields(name, username, password)
        if (validateFields) {
            viewModel.registerUser(User(null, name, username, password))
            viewModel.registerResponse.observe(this){
                when(it.status){
                    Status.SUCCESS -> {
                        binding.activityRegisterProgressCircular.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, "Sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    Status.LOADING -> {
                        binding.activityRegisterProgressCircular.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.activityRegisterProgressCircular.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        Log.d("***Toast", it.message.toString())
                    }
                }
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFields(name: String, username: String, password: String): Boolean {
        return name.isNotBlank() && username.isNotBlank() && password.isNotBlank()
    }

}