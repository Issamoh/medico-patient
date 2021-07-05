package com.example.medico.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.medico.R
import com.example.medico.ui.booking.DetailsBookingFragment


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navController = this.findNavController(R.id.navHotFromHome)
       // navController.navigate(R.id.detailsBookingFragment)
        navController.navigate(R.id.mesRdvFragment)

    }
}