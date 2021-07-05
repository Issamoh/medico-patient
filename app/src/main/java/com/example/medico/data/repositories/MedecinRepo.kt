package com.example.medico.data.repositories

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.Medecin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MedecinRepo {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getAllMedecins(): MutableLiveData<List<Medecin>>{
            val call = api.getAllMedecins()
            var data : List<Medecin>?
            var finalMed = MutableLiveData<List<Medecin>>()

            call.enqueue(object : Callback<List<Medecin>> {
                override fun onFailure(call: Call<List<Medecin>>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }

                override fun onResponse(
                    call: Call<List<Medecin>>,
                    response: Response<List<Medecin>>
                ) {
                    if(response.isSuccessful){
                        data = response.body()
                        if(data != null){
                            finalMed.value = data!!
                        }
                    }else{
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalMed
        }
    }
}