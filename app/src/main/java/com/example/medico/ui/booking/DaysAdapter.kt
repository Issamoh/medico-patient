package com.example.medico.ui.booking

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
import com.example.medico.data.model.Day
import com.example.medico.data.model.RdvViewModel

class daysAdapter(val context: Context, var data: List<Day>): RecyclerView.Adapter<MyViewHolder>() {
    var selectedItem = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].nom
        holder.num.text = data[position].num.toString()
        if (selectedItem == position) {
            val vm = ViewModelProvider(context as ViewModelStoreOwner).get(RdvViewModel::class.java)
            vm.jour = data[position].num
            Toast.makeText(context, vm.jour.toString(), Toast.LENGTH_SHORT).show()
            holder.containerDay.background = ContextCompat.getDrawable(
                context,
                R.drawable.selected_day_bg
            );
            holder.nom.setTextColor(Color.parseColor("#FFFFFF"));
            holder.num.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.containerDay.background = ContextCompat.getDrawable(
                context,
                R.drawable.unselected_day_bg
            );
            holder.nom.setTextColor(Color.parseColor("#898C8C"));
            holder.num.setTextColor(Color.parseColor("#898C8C"));
        }
        holder.itemView.setOnClickListener {
            val previousItem = selectedItem
            selectedItem = position
            notifyItemChanged(previousItem)
            notifyItemChanged(position)
        }

    }
    override fun getItemCount() = data.size
}
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val nom= view.findViewById<TextView>(R.id.nomDay)
    val num= view.findViewById<TextView>(R.id.numDay)
    val containerDay = view.findViewById<LinearLayout>(R.id.containerDay)
}
