package com.example.medico.ui.traitement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medico.data.model.Traitement
import com.example.medico.data.repositories.TraitementRepo

class TraitementViewModel:ViewModel() {
    var listTraitementsPatient = MutableLiveData<List<Traitement>>()
    var currentTraitement = MutableLiveData<Traitement>()

    fun setCurTraitement(curTrait: Traitement){
        currentTraitement.value = curTrait
    }

    fun getTraitementsPatient(id:String){
        listTraitementsPatient = TraitementRepo.getTraitementsPatient(id)
    }
}