package com.example.medico.ui.rendezVous

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.RdvRecord
import com.example.medico.data.model.RdvViewModel

class RdvAdapter (val context: Context, var data: List<RdvRecord>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rdv_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].nomMedecin
        holder.spec.text = data[position].specMedecin
        holder.date.text = data[position].date
        holder.heure.text = data[position].heure
        holder.itemView.setOnClickListener {
        }

    }
    override fun getItemCount() = data.size
}
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val nom= view.findViewById<TextView>(R.id.nomMedecinL)
    val spec= view.findViewById<TextView>(R.id.specMedecinL)
    val date = view.findViewById<TextView>(R.id.dateRdvL)
    val heure = view.findViewById<TextView>(R.id.heureRdv)
}
