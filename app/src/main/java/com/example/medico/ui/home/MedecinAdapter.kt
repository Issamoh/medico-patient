package com.example.medico.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medico.R
import com.example.medico.data.model.Medecin

class MedecinAdapter(val context: Context, var data:List<Medecin>): RecyclerView.Adapter<MedecinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedecinViewHolder {
        return MedecinViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_doctor_layout, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MedecinViewHolder, position: Int){
        val elt = data[position]
        Toast.makeText(context, elt.nomMedecin, Toast.LENGTH_LONG)
        Glide.with(context).load(elt.photoMedecin).into(holder.image)
        holder.nom.text = holder.nom.text.toString() + " " + elt.nomMedecin + " " + elt.prenomMedecin
        holder.numero.text = elt.telephoneMedecin
        //En attendant de récupérer le nom de la spécialité
        holder.spec.text = elt.idSpecialite.toString()

        holder.numero.setOnClickListener {
            val uri = Uri.parse("tel:${data[position].telephoneMedecin}")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

        holder.pos.setOnClickListener{
            val longitude = data[position].cabinetMedecinLongitude
            val latitude = data[position].cabinetMedecinLatitude
            val geoLocation = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW,geoLocation)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

        /*holder.itemView.setOnClickListener {
            var bundle = bundleOf("pos" to position)
            it.findNavController().navigate(R.id.action_medecinsFragment2_to_detailsFragment, bundle)
        }*/
    }

}

class MedecinViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val image = view.findViewById<ImageView>(R.id.docImg) as ImageView
    val nom = view.findViewById<TextView>(R.id.docName) as TextView
    val numero = view.findViewById<TextView>(R.id.docNum) as TextView
    val spec = view.findViewById<TextView>(R.id.docSpec) as TextView
    val pos = view.findViewById<ImageView>(R.id.docPos) as ImageView
}