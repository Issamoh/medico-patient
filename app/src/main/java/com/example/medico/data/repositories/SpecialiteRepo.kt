package com.example.medico.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.Medecin
import com.example.medico.data.model.Specialite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialiteRepo {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getAllSpecialites(): MutableLiveData<List<Specialite>> {
            val call = api.getAllSpecialites()
            var data : List<Specialite>?
            var finalSpec = MutableLiveData<List<Specialite>>()

            call.enqueue(object : Callback<List<Specialite>> {
                override fun onFailure(call: Call<List<Specialite>>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }

                override fun onResponse(
                    call: Call<List<Specialite>>,
                    response: Response<List<Specialite>>
                ) {
                    if(response.isSuccessful){
                        data = response.body()
                        if(data != null){
                            finalSpec.value = data!!
                        }
                    }else{
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalSpec
        }
    }
}