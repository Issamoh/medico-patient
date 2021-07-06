package com.example.medico.ui.traitement

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Traitement
import com.example.medico.ui.home.MedecinViewHolder
import com.example.medico.ui.home.MedecinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TraitementAdapter(val context: Context,
                         var data: List<Traitement>,
                         var dataVM: TraitementViewModel): RecyclerView.Adapter<TraitementViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitementViewHolder {
        //On ordonne les médecins par note
        data = data.sortedByDescending { it.dateDebutTraitement }
        return TraitementViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_traitement_layout, parent, false))

    }

    override fun getItemCount() = data.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TraitementViewHolder, position: Int){
        val elt = data[position]

        holder.idTrait.text = holder.idTrait.text.toString() + " " + elt.idTraitement.toString()
        holder.dateDebut.text = holder.dateDebut.text.toString() + " " + elt.dateDebutTraitement.take(10)
        holder.dureeTrait.text = holder.dureeTrait.text.toString() + " " + elt.dureeTraitement + " jours"
        holder.rmqTrait.text = holder.rmqTrait.text.toString() + " " + elt.remarquesTraitement

        holder.itemView.setOnClickListener {
            /*val medVM = ViewModelProvider((context as FragmentActivity)).get(MedecinViewModel::class.java)
            medVM.setMedById(elt.idMedecin)*/
            dataVM.setCurTraitement(elt)

            it.findNavController().navigate(R.id.action_traitementsFragment_to_detailsTraitementFragment)
        }
    }
}


class TraitementViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val idTrait = view.findViewById<TextView>(R.id.numTrait) as TextView
    val dateDebut = view.findViewById<TextView>(R.id.startDate) as TextView
    val dureeTrait = view.findViewById<TextView>(R.id.dureeTrait) as TextView
    val rmqTrait = view.findViewById<TextView>(R.id.rmqTrait) as TextView
}