package com.pplgskanic.newsapp.ui

import android.os.Bundle
import android.view.View
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pplgskanic.newsapps.R
import com.pplgskanic.newsapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_bookmark, R.id.navigation_categories -> {
                    binding.navView.visibility = View.VISIBLE
                }
                else -> {
                    binding.navView.visibility = View.GONE
                }
            }
        }
        binding.navView.setupWithNavController(navController)
    }
}