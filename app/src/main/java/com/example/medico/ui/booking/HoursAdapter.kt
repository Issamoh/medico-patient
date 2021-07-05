package com.example.medico.ui.booking

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Hour
import com.example.medico.data.model.RdvViewModel

class HoursAdapter(val context: Context, var data:List<Hour>): RecyclerView.Adapter<MatinViewHolder>() {
    var selectedItem = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hour_item, parent, false)
        return MatinViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatinViewHolder, position: Int) {
        holder.valeur.text = data[position].valeur
        if (selectedItem == position) {
            val vm = ViewModelProvider( context as ViewModelStoreOwner).get(RdvViewModel::class.java)
            vm.heure = data[position].valeur
            Toast.makeText(context, vm.heure, Toast.LENGTH_SHORT).show()
            holder.containerHour.background = ContextCompat.getDrawable(context, R.drawable.selected_day_bg);
            holder.valeur.setTextColor(Color.parseColor("#FFFFFF"));
            holder.imageClock.setBackgroundResource(R.drawable.ic_white_clock);
        }else{
            holder.containerHour.background = ContextCompat.getDrawable(context, R.drawable.unselected_day_bg);
            holder.valeur.setTextColor(Color.parseColor("#1D2424"));
            holder.imageClock.setBackgroundResource(R.drawable.ic_clock);
        }
      holder.itemView.setOnClickListener{
          val previousItem = selectedItem
          selectedItem = position
          notifyItemChanged(previousItem)
          notifyItemChanged(position)
    }
    }

    override fun getItemCount() = data.size

}

class MatinViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val valeur= view.findViewById<TextView>(R.id.valeur)
    val containerHour = view.findViewById<LinearLayout>(R.id.containerHour)
    val imageClock = view.findViewById<ImageView>(R.id.imageClock)
}
