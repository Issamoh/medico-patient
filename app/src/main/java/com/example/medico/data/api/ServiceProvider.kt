package com.example.medico.data.api

import com.example.medico.data.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ServiceProvider {

    //authentification patient
    @POST("api/auth/patient")
    fun userLogin(
        @Body info: SignInBody
    ): Call<LoginUser>

    //get medecins
    @GET("api/medecins/all")
    fun getAllMedecins(): Call<List<Medecin>>

    //get specialites
    @GET("api/specialites/all")
    fun getAllSpecialites(): Call<List<Specialite>>

    //Demande rdv
    @POST("api/rdv/demande")
    fun prendreRdv(
        @Body info: DemandeRdv
    ): Call<DemandeRdvResponse>

    //get mes rendez-vous
    @POST("api/rdv/patient/{id}")
    fun mesRdv(
        @Path("id") id:String
    ): Call<List<RdvRecord>>

    //get a patient
    @GET("api/patients/{id}")
    fun getPatient(
        @Path("id") id:String
    ):Call<Patient>

}
