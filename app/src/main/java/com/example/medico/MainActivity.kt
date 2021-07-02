package com.example.medico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medico.ui.home.HomeActivity
import com.example.medico.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //redirecting to login activity, login activity will redirect to home activity if the user is already logged in or if he succeed auth
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}