package com.example.medico.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.Medicament
import com.example.medico.data.model.TraitMed
import com.example.medico.data.model.Traitement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicamentRepo {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getMedicTraitement(id: Int): MutableLiveData<List<TraitMed>>{
            var call = api.getMedicamentsByTraitement(id)
            var data: List<TraitMed>?
            var finalTraitMed = MutableLiveData<List<TraitMed>>()

            call.enqueue(object : Callback<List<TraitMed>> {
                override fun onFailure(call: Call<List<TraitMed>>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }
                override fun onResponse(
                    call: Call<List<TraitMed>>,
                    response: Response<List<TraitMed>>
                ) {
                    if (response.isSuccessful) {
                        data = response.body()
                        if (data != null) {
                            finalTraitMed.value = data!!
                        }
                    } else {
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalTraitMed
        }

        fun getMedicById(id: Int): MutableLiveData<Medicament>{
            var call = api.getMedicamentsById(id)
            var data: Medicament?
            var finalMedic = MutableLiveData<Medicament>()

            call.enqueue(object : Callback<Medicament> {
                override fun onFailure(call: Call<Medicament>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }
                override fun onResponse(
                    call: Call<Medicament>,
                    response: Response<Medicament>
                ) {
                    if (response.isSuccessful) {
                        data = response.body()
                        if (data != null) {
                            finalMedic.value = data!!
                        }
                    } else {
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalMedic
        }


    }
}