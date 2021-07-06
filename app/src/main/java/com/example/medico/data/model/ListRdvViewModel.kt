package com.example.medico.data.model

import androidx.lifecycle.ViewModel
import com.example.medico.ui.rendezVous.MesRdvFragment

class ListRdvViewModel: ViewModel() {
    lateinit var subscriber : MesRdvFragment
    var list = mutableListOf<RdvRecord>()

    fun modifyAndNotify(data : MutableList<RdvRecord>){
        list = data
        if(subscriber!=null){
            subscriber.dataIsReady()
        }

    }
}