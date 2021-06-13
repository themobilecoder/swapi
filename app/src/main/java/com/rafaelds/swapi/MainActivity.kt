package com.rafaelds.swapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        setupToolbarForNavigation()
    }

    private fun setupToolbarForNavigation() {
        val host: NavHostFragment? = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment?
        val navController = host?.navController
        navController?.let {
            setupActionBarWithNavController(this, it)
        }
    }
}