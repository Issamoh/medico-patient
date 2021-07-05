package com.example.medico.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.medico.data.model.Medecin
import com.example.medico.data.repositories.MedecinRepo

class MedecinViewModel:ViewModel() {
    var listMed = MutableLiveData<List<Medecin>>()
    var currentMed = MutableLiveData<Medecin>()

    fun getAllMedecins(){
        listMed = MedecinRepo.getAllMedecins()
    }

    fun setMed(med: Medecin){
        currentMed.value = med
    }
}