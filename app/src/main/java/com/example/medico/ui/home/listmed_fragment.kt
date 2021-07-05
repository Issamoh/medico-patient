package com.example.medico.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import kotlinx.android.synthetic.main.fragment_listmed_fragment.*

class listmed_fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listmed_fragment, container, false)!!
        // Inflate the layout for this fragment
        /*val view:View = inflater.inflate(R.layout.fragment_listmed_fragment, container, false)!!

        val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        medVM.getAllMedecins()

        val rv:RecyclerView = view.findViewById(R.id.allMeds) as RecyclerView

        medVM.listMed.observe(requireActivity(), Observer { med ->
            rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = MedecinAdapter(requireActivity(), med)
        })

        return view*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        medVM.getAllMedecins()

        val rv:RecyclerView = view?.findViewById(R.id.allMeds) as RecyclerView

        medVM.listMed.observe(requireActivity(), Observer { med ->
            rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = MedecinAdapter(requireActivity(), med)
        })
    }
}

