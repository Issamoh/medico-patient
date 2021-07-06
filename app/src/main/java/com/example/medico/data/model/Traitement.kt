package com.example.medico.data.model


data class Traitement (
    var idTraitement: Int,
    var dateDebutTraitement: String,
    var dureeTraitement: String,
    var remarquesTraitement: String,
    var idMedecin: Int,
    var idPatient: Int
)