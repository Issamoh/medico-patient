package com.example.medico.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Medecin
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_listmed_fragment.*

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

        //On paramètre le menu
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.appBar)
        val drawLay = view?.findViewById<DrawerLayout>(R.id.frag_homepage)
        val drawView = view?.findViewById<NavigationView>(R.id.navigation_view)

        toolbar?.setNavigationOnClickListener{
            drawLay?.openDrawer(GravityCompat.START)
        }

        drawView?.setNavigationItemSelectedListener{
            val id: Int = it.itemId
            drawLay?.closeDrawer(GravityCompat.START)
            when (id) {
                R.id.nav_rdv -> findNavController().navigate(R.id.action_homepageFragment_to_mesRdvFragment)
                R.id.nav_trait -> Toast.makeText(
                    context,
                    "Traitement is Clicked",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_msg -> Toast.makeText(
                    context,
                    "Conseil is Clicked",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_params -> Toast.makeText(
                    context,
                    "Params is Clicked",
                    Toast.LENGTH_SHORT
                ).show()
                else -> return@setNavigationItemSelectedListener true
            }
            return@setNavigationItemSelectedListener true
        }


        //On récupère les données et on affiche les recyclerView
        specVM = ViewModelProvider(requireActivity()).get(SpecialiteViewModel::class.java)
        medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)


        medVM.getAllMedecins()
        specVM.getAllSpecialites()

        val rv:RecyclerView = view?.findViewById(R.id.recyclerViewMed) as RecyclerView

        medVM.listMed.observe(requireActivity(), Observer { med ->
            rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = MedecinAdapter(requireActivity(), med, medVM, "note", specVM)
        })
        
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