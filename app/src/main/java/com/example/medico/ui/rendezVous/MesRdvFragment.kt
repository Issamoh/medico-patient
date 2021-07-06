package com.example.medico.ui.rendezVous

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.ListRdvViewModel
import com.example.medico.data.repositories.RdvRepository
import com.example.medico.ui.booking.daysAdapter

class MesRdvFragment : Fragment() {

   lateinit var view1 : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mes_rdv, container, false)
        val vm= ViewModelProvider(context as ViewModelStoreOwner).get(ListRdvViewModel::class.java)
        vm.subscriber = this
        view1 = view
        RdvRepository.mesRdv(view.context)
        val backButtonList =  view.findViewById(R.id.backButtonList) as ImageButton
        backButtonList.setOnClickListener{
            val navController = (context as Activity).findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_mesRdvFragment_to_homepageFragment)
        }
        return view
    }

    fun dataIsReady() {
        val vm= ViewModelProvider(context as ViewModelStoreOwner).get(ListRdvViewModel::class.java)
        val  recyclerView = view1.findViewById<RecyclerView>(R.id.mesRdv)
        recyclerView.layoutManager = LinearLayoutManager(view1.context)
        recyclerView.adapter = RdvAdapter(
            view1.context,vm.list)
    }


}