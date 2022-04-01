package com.example.testecomponentizacao.view

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testecomponentizacao.databinding.ActivitySplashBinding
import com.example.testecomponentizacao.utils.Utils
import com.example.testecomponentizacao.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class IntroductionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: AuthViewModel


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Utils.hideStatusBar(window)
        verifySavedPreferences()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun <T> navigateToActivity(activity: Class<T>) {
        Handler().postDelayed({
            val intent = Intent(this, activity)
            startActivity(intent)
            finish()
        }, 4000)
    }

    private fun verifySavedPreferences() {
        lifecycleScope.launch {
            viewModel.username.observe(this@IntroductionActivity){user ->
                viewModel.password.observe(this@IntroductionActivity){ pass ->
                    Log.d("***Activity", "User: $user - Pass: $pass")
                    if (user.isNotEmpty() && pass.isNotEmpty()){
                        navigateToActivity(HomeLoggedActivity::class.java)
                    }else{
                        navigateToActivity(LoginActivity::class.java)
                    }
                }
            }
            //Log.d("***Activity", "$user - $pass")
            //val pass = viewModel.username.value
        }
    }

}