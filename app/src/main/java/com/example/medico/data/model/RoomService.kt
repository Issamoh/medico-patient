package com.example.medico.data.model

import android.annotation.SuppressLint
import androidx.room.Room
import android.content.Context

@SuppressLint("StaticFieldLeak")
object RoomService {

    lateinit var context:Context

    val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java,"conseilsdb").allowMainThreadQueries().build()
    }



}