package com.example.medico.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Medecin
import kotlinx.android.synthetic.main.fragment_homepage.*

class HomepageFragment : Fragment() {
    private lateinit var medVM: MedecinViewModel
    private lateinit var specVM: SpecialiteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        medVM.getAllMedecins()

        val rv:RecyclerView = view?.findViewById(R.id.recyclerViewMed) as RecyclerView

        medVM.listMed.observe(requireActivity(), Observer { med ->
            rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = MedecinAdapter(requireActivity(), med, medVM)

        })

        specVM = ViewModelProvider(requireActivity()).get(SpecialiteViewModel::class.java)
        specVM.getAllSpecialites()

        val rvS:RecyclerView = view?.findViewById(R.id.recyclerViewSpec) as RecyclerView

        specVM.listSpec.observe(requireActivity(), Observer { spec ->
            rvS.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvS.adapter = SpecialiteAdapter(requireActivity(), spec)
        })

        listDocDisplay.setOnClickListener{
            findNavController().navigate(R.id.action_homepageFragment_to_listmed_fragment)
        }
    }
}