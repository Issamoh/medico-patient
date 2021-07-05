package com.example.medico.data.repositories

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.DemandeRdv
import com.example.medico.data.model.DemandeRdvResponse
import com.example.medico.data.model.LoginUser
import com.example.medico.data.model.SignInBody
import com.example.medico.ui.home.HomeActivity
import com.example.medico.utils.sharedPrefFile
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RdvRepository {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        fun prendreRdv(
            context: Context,
            dmd: DemandeRdv
        ) {

            val dmdRdvRequest = api.prendreRdv(dmd) // consommation de l'api

            dmdRdvRequest.enqueue(object : Callback<DemandeRdvResponse> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<DemandeRdvResponse>, response: Response<DemandeRdvResponse>) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: LoginUser = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            LoginUser::class.java
                        )
                        Toast.makeText(context, message.msg, Toast.LENGTH_LONG).show()

                    } else {
                        val resp = response.body()
                        if (resp != null) {
                            if(resp.success){
                                Toast.makeText(context, resp.msg, Toast.LENGTH_LONG).show()
                             /*TODO maybe redirect to fragment where qr code   val myIntent = Intent(context, HomeActivity::class.java)
                                context.startActivity(myIntent)
                                (context as Activity).finish()*/

                            }
                            else{
                                Toast.makeText(context, resp.msg, Toast.LENGTH_LONG).show()

                            }
                        }

                    }
                }

                override fun onFailure(call: Call<DemandeRdvResponse>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}