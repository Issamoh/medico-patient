package com.example.medico.ui.traitement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medico.data.model.Medicament
import com.example.medico.data.model.TraitMed
import com.example.medico.data.repositories.MedicamentRepo

class MedicamentViewModel:ViewModel() {
    var traitMeds = MutableLiveData<List<TraitMed>>()
    var medic = MutableLiveData<Medicament>()

    fun getMedicTraitement(id: Int){
        traitMeds = MedicamentRepo.getMedicTraitement(id)
    }

    fun getMedicById(id: Int){
        medic = MedicamentRepo.getMedicById(id)
    }
}