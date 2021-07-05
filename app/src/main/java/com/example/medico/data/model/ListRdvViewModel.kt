package com.example.medico.data.model

import androidx.lifecycle.ViewModel

class ListRdvViewModel: ViewModel() {
    var list = mutableListOf<RdvRecord>()
}