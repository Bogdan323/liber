package com.example.liber.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.liber.R
import com.example.liber.databinding.ActivityShoppingBinding
import com.example.liber.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityShoppingBinding::inflate)
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shoppingHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
    }
}