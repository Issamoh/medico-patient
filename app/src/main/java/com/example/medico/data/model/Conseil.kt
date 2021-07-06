package com.example.medico.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conseils")
data class Conseil(
    val idPatient:String,
    val idMedecin:String,
    val demandePatient:String,
    var isSynchronized:Int=0
){
    @PrimaryKey(autoGenerate = true)
    var conseilID: Long?=null
}
