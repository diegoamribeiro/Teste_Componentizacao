package com.example.testecomponentizacao.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.testecomponentizacao.databinding.ActivityLoginBinding
import com.example.testecomponentizacao.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        hideStatusBar()
        hideKeyboard(this)

        binding.activityLoginButtonLogin.setOnClickListener {
            val intent = Intent(this, HomeLoggedActivity::class.java)
            startActivity(intent)
        }
        setContentView(view)
    }

    private fun hideStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}

