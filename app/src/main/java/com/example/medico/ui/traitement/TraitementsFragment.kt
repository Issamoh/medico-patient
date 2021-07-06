package com.example.medico.ui.traitement

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.ui.home.SpecialiteViewModel
import com.example.medico.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_traitements.*


class TraitementsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_traitements, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharePref = (context as Activity).getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val id = sharePref.getString("userID", "0")

        val rv: RecyclerView = view?.findViewById(R.id.allTraitsPatient) as RecyclerView

        val traitVM = ViewModelProvider(requireActivity()).get(TraitementViewModel::class.java)
        traitVM.getTraitementsPatient(id!!)

        traitVM.listTraitementsPatient.observe(requireActivity(), { traits ->
            rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = TraitementAdapter(requireActivity(), traitVM.listTraitementsPatient.value!!, traitVM)
        })


        backButtonTrait.setOnClickListener{
            findNavController().navigate(R.id.action_traitementsFragment_to_homepageFragment)
        }

    }

}