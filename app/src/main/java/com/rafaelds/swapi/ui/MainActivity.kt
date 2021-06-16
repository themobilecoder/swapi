package com.rafaelds.swapi.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.rafaelds.swapi.R

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitivy_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.main_dest))
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        setupToolbarForNavigation(appBarConfiguration)
    }

    private fun setupToolbarForNavigation(appBarConfiguration: AppBarConfiguration) {
        val host: NavHostFragment? = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment?
        val navController = host?.navController
        navController?.let {
            NavigationUI.setupActionBarWithNavController(this, it, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp() || super.onSupportNavigateUp()
    }
}