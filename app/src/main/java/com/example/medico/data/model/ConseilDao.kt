package com.example.medico.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ConseilDao {
    @Insert
    fun addConseil(conseil: Conseil)

    @Update
    fun updateConseil(conseil: Conseil)

    @Query("select * from conseils where isSynchronized=0")
    fun getNonSynConseils():List<Conseil>

    @Update
    fun updateConseils(teams: List<Conseil>)

}