package com.example.medico.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.example.medico.data.model.Medecin
import com.example.medico.data.model.Patient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientRepo {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun getPatient(id: String): MutableLiveData<Patient> {
            var call = api.getPatient(id)
            var data: Patient?
            var finalData = MutableLiveData<Patient>()

            call.enqueue(object : Callback<Patient> {
                override fun onFailure(call: Call<Patient>, t: Throwable) {
                    Log.d("Reponse", "Erreur : ", t)
                }
                override fun onResponse(
                    call: Call<Patient>,
                    response: Response<Patient>
                ) {
                    if(response.isSuccessful){
                        data = response.body()
                        if(data != null){
                            finalData.value = data!!
                        }
                    }else{
                        println("Une erreur s'est déroulée lors de la récupération des données")
                    }
                }
            })
            return finalData
        }

    }
}