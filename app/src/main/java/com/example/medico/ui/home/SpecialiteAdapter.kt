package com.example.medico.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medico.R
import com.example.medico.data.model.Medecin
import com.example.medico.data.model.Specialite

class SpecialiteAdapter(val context: Context, var data:List<Specialite>): RecyclerView.Adapter<SpecialiteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialiteViewHolder {
        return SpecialiteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.listitem_specialite_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SpecialiteViewHolder, position: Int) {
        val elt = data[position]
        holder.nom.text = elt.nomSpecialite
        Glide.with(context).load(elt.iconeSpecialite).into(holder.image)
    }

    /*holder.itemView.setOnClickListener {
            var bundle = bundleOf("pos" to position)
            it.findNavController().navigate(R.id.action_medecinsFragment2_to_detailsFragment, bundle)
        }*/
}

class SpecialiteViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val image = view.findViewById<ImageView>(R.id.imgSpec) as ImageView
    val nom = view.findViewById<TextView>(R.id.txtSpec) as TextView
}