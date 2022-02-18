package com.example.testecomponentizacao.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testecomponentizacao.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeLoggedActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_logged)

        val homeContainerView = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = homeContainerView.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}