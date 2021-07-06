package com.example.medico.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.Medecin
import com.example.medico.data.model.Traitement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TraitementRepo {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getTraitementsPatient(id: String): MutableLiveData<List<Traitement>>{
            var call = api.getTraitementsPatient(id)
            var data: List<Traitement>?
            var finalTraitements = MutableLiveData<List<Traitement>>()

            call.enqueue(object : Callback<List<Traitement>> {
                override fun onFailure(call: Call<List<Traitement>>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }
                override fun onResponse(
                    call: Call<List<Traitement>>,
                    response: Response<List<Traitement>>
                ) {
                    if (response.isSuccessful) {
                        data = response.body()
                        if (data != null) {
                            finalTraitements.value = data!!
                        }
                    } else {
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalTraitements
        }

    }
}