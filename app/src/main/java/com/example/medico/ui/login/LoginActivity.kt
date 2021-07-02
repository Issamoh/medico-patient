package com.example.medico.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medico.R
import com.example.medico.data.repositories.LoginRepo
import com.example.medico.ui.home.HomeActivity
import com.example.medico.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        start()

        loginButton.setOnClickListener {

            val tel = tel.text.toString()
            val password = pwd.text.toString()

            if (tel.isEmpty()) {
                /*TODO Email.error = "Email Required"
                Email.requestFocus()
                return@setOnClickListener*/
            }
            if (password.isEmpty()) {
               /*TODO Pass.error = "Password Required"
                Pass.requestFocus()
                return@setOnClickListener*/
            }

            var loginRepo = LoginRepo.Companion
            loginRepo.login(this, tel, password)

        }
    }

    fun start() {
        val sharedPref = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        val con = sharedPref.getBoolean("connected",false)
        if (con){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}