package com.example.medico.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medico.R
import com.example.medico.data.model.Medecin
import com.example.medico.data.repositories.MedecinRepo
import com.example.medico.data.repositories.SpecialiteRepo
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_homepage.view.*

class HomepageFragment : Fragment() {
    private lateinit var medVM: MedecinViewModel
    private lateinit var specVM: SpecialiteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        //val specVM = ViewModelProvider(requireActivity()).get(SpecialiteViewModel::class.java)

        medVM.getAllMedecins()
        /*specVM.getAllSpecialites()

        recyclerViewSpec.layoutManager = LinearLayoutManager(requireActivity())
        recyclerViewSpec.adapter = SpecialiteAdapter(requireActivity(), specVM.listSpec)*/

        recyclerViewMed.layoutManager = LinearLayoutManager(requireActivity())
        recyclerViewMed.adapter = MedecinAdapter(requireActivity(), medVM.listMed)

        /*listDocDisplay.setOnClickListener{

        }*/
    }
}