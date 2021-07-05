package com.example.medico.ui.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Day
import com.example.medico.data.model.RdvViewModel

class daysAdapter(val context: Context, var data:List<Day>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].nom
        holder.num.text = data[position].num.toString()
        holder.itemView.setOnClickListener {
            val vm = ViewModelProvider( context as ViewModelStoreOwner).get(RdvViewModel::class.java)
                vm.jour = data[position].num
            Toast.makeText(context, vm.jour.toString(), Toast.LENGTH_SHORT).show()
        }

    }
    override fun getItemCount() = data.size
}
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val nom= view.findViewById<TextView>(R.id.nomDay)
    val num= view.findViewById<TextView>(R.id.numDay)
}
