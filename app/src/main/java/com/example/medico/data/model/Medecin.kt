package com.example.medico.data.model

data class Medecin(
    var idMedecin: Int,
    var telephoneMedecin: String,
    var passwordMedecin: String,
    var emailMedecin: String,
    var photoMedecin: String,
    var nomMedecin: String,
    var prenomMedecin: String,
    var idSpecialite: Int,
    var cabinetMedecinLongitude: Float,
    var cabinetMedecinLatitude: Float,
    var noteMedecin: Float
)
