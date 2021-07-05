package com.example.medico.data.repositories

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import com.example.medico.R
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.*
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
                                //TODO make doctor inside viewModel
                                val navController = (context as Activity).findNavController(R.id.navHotFromHome)
                                navController.navigate(R.id.action_detailsBookingFragment_to_detailsRdvFragment)

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
    fun mesRdv(
        context: Context
    ) {
        val sharedPref = (context as Activity).getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )
        val id = sharedPref.getString("userID", "0")

        val dmdRdvRequest = api.mesRdv(id!!) // consommation de l'api

        dmdRdvRequest.enqueue(object : Callback<List<RdvRecord>> {

            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<List<RdvRecord>>, response: Response<List<RdvRecord>>) {
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
                            Toast.makeText(context,"got data", Toast.LENGTH_SHORT).show()
                            val vm= ViewModelProvider(context as ViewModelStoreOwner).get(ListRdvViewModel::class.java)
                            vm.list = resp.toMutableList()
                    }
                }
            }

            override fun onFailure(call: Call<List<RdvRecord>>, t: Throwable) {
                Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
            }
        })
    }
}