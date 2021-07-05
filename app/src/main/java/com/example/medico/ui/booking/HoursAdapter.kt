package com.example.medico.ui.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Hour
import com.example.medico.data.model.RdvViewModel

class HoursAdapter(val context: Context, var data:List<Hour>): RecyclerView.Adapter<MatinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hour_item, parent, false)
        return MatinViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatinViewHolder, position: Int) {
        holder.valeur.text = data[position].valeur
      holder.itemView.setOnClickListener{
          val vm = ViewModelProvider( context as ViewModelStoreOwner).get(RdvViewModel::class.java)
          vm.heure = data[position].valeur
          Toast.makeText(context, vm.heure, Toast.LENGTH_SHORT).show()
    }
    }

    override fun getItemCount() = data.size

}

class MatinViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val valeur= view.findViewById<TextView>(R.id.valeur)
}
