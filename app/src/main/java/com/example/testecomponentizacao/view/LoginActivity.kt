package com.example.testecomponentizacao.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.testecomponentizacao.R
import com.example.testecomponentizacao.RegisterActivity
import com.example.testecomponentizacao.data.remote.NetworkStatus
import com.example.testecomponentizacao.data.remote.NetworkStatusHelper
import com.example.testecomponentizacao.databinding.ActivityLoginBinding
import com.example.testecomponentizacao.utils.Utils.hideKeyboard
import com.example.testecomponentizacao.utils.Utils.hideStatusBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //checkInternetConnection()
        setContentView(view)
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun showSnackBar(message: String, color: Int, length: Int) {
//        Snackbar.make(findViewById(R.id.login_content), message, length)
//            .setAction("OK", null)
//            .setBackgroundTint(color).show()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun checkInternetConnection() {
//        NetworkStatusHelper(this).observe(this){
//            when(it) {
//                NetworkStatus.Unavailable ->
//                    showSnackBar(
//                        "No Connection! Data could be out dated",
//                        ContextCompat.getColor(this, R.color.vermilion),
//                        Snackbar.LENGTH_INDEFINITE
//                    )
//                NetworkStatus.Available ->
//                    showSnackBar("Connected", ContextCompat.getColor(this,
//                        R.color.green), Snackbar.LENGTH_SHORT)
//            }
//        }
//    }
}

