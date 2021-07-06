package com.example.medico.data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Conseil::class),version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getConseilDao():ConseilDao

}