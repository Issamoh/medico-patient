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

    //Demande rdv
    @POST("api/rdv/demande")
    fun prendreRdv(
        @Body info: DemandeRdv
    ): Call<DemandeRdvResponse>

    //get mes rendez-vous
    @GET("api/rdv/patient/{id}")
    fun mesRdv(
        @Path("id") id:String
    ): Call<List<RdvRecord>>

}
