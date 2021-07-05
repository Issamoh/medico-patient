package com.example.medico.ui.home

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

import com.example.medico.R

import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView



class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: NavigationView = NavigationView(this)

        val nav = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(nav)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()

    }
}