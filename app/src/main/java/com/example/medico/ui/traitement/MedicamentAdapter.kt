package com.example.medico.ui.traitement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Medicament
import java.time.LocalDate

class MedicamentAdapter(val context: Context, var data: List<Medicament>): RecyclerView.Adapter<MedicamentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentViewHolder {
        return MedicamentViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.listitem_medicament_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: MedicamentViewHolder, position: Int) {
        val elt = data[position]

        holder.nomMedic.text = holder.nomMedic.text.toString() + " " + elt.nomMedicament
        holder.typeMedic.text = holder.typeMedic.text.toString() + " " + elt.typeMedicament
        holder.modMedic.text = holder.modMedic.text.toString() + " " + elt.priseMedicament
    }
}

class MedicamentViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val nomMedic = view.findViewById<TextView>(R.id.nomMedic) as TextView
    val typeMedic = view.findViewById<TextView>(R.id.typeMedic) as TextView
    val modMedic = view.findViewById<TextView>(R.id.modMedic) as TextView
}