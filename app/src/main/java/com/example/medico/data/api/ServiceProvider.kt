package com.example.medico.data.api

import com.example.medico.data.model.*
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
    @GET("api/rdv/patient/{id}")
    fun mesRdv(
        @Path("id") id:String
    ): Call<List<RdvRecord>>

    //get a patient
    @GET("api/patients/{id}")
    fun getPatient(
        @Path("id") id:String
    ):Call<Patient>

    @POST("api/conseil/multipledemande")
    fun addTeams(@Body conseils: List<Conseil>):Call<ResponseBack>

    @POST("api/conseil/demande")
    fun addConseil(@Body conseil: Conseil):Call<ResponseBack>

    //traitements d'un patient
    @GET("api/traitements/patient/{id}")
    fun getTraitementsPatient(
        @Path("id") id:String
    ):Call<List<Traitement>>

    //medecin by id
    @GET("api/medecins/{id}")
    fun getMedecinById(
        @Path("id") id:Int
    ):Call<Medecin>

    //medicament by id
    @GET("api/medicaments/{id}")
    fun getMedicamentsById(
        @Path("id") id:Int
    ):Call<Medicament>

    //medicaments d'un traitement
    @GET("api/medicaments/traitement/{id}")
    fun getMedicamentsByTraitement(
        @Path("id") id: Int
    ):Call<List<TraitMed>>
}
