package com.example.medico.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medico.data.model.Specialite
import com.example.medico.data.repositories.SpecialiteRepo

class SpecialiteViewModel:ViewModel() {
    var listSpec = listOf<Specialite>()

    fun getAllSpecialites(){
        listSpec = SpecialiteRepo.getAllSpecialites()
    }
}