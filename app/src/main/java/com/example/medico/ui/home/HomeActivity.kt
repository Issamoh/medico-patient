package com.example.medico.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medico.R
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val nav = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(nav)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}