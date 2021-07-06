package com.example.medico.ui.traitement

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medico.R
import com.example.medico.data.model.Medicament
import kotlinx.android.synthetic.main.fragment_details_traitement.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DetailsTraitementFragment : Fragment() {
       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_traitement, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)

        println(medVM.medById.value?.nomMedecin)

        medVM.medById.observe(requireActivity(), { med ->
            println("NOM MEDECIN")
            val imgMed = view?.findViewById<ImageView>(R.id.medTrait) as ImageView
            Glide.with(requireActivity()).load(med.photoMedecin).circleCrop().into(imgMed)

            val nomMed = view?.findViewById<TextView>(R.id.nomDocTrait) as TextView
            nomMed.text = nomMed.text.toString() + " " + med.nomMedecin + med.prenomMedecin

            val numMed = view?.findViewById<TextView>(R.id.DocTraitNum) as TextView
            numMed.text = med.telephoneMedecin
        })*/

        val rv: RecyclerView = view?.findViewById(R.id.medicaTrait) as RecyclerView

        val traitVM = ViewModelProvider(requireActivity()).get(TraitementViewModel::class.java)
        val medicVM = ViewModelProvider(requireActivity()).get(MedicamentViewModel::class.java)
        traitVM.currentTraitement.observe(requireActivity(), { trait ->
            val dateD = view?.findViewById<TextView>(R.id.debutDetTrait) as TextView
            dateD.text = dateD.text.toString() + " " + trait.dateDebutTraitement.take(10)

            val dureeD = view?.findViewById<TextView>(R.id.dureeDetTrait) as TextView
            dureeD.text = dureeD.text.toString() + " " + trait.dureeTraitement + " jours"

            val rmq = view?.findViewById<TextView>(R.id.rmqDetTrait) as TextView
            rmq.text = rmq.text.toString() + " " + trait.remarquesTraitement

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val ld = LocalDate.parse(trait.dateDebutTraitement.take(10), formatter)
            val newDate = ld.plusDays(trait.dureeTraitement.toLong())
            val currentDate = LocalDate.now()
            currentDate.format(formatter)

            val cur = currentDate.compareTo(newDate)
            if(cur <= 0){
                val validite = view?.findViewById<TextView>(R.id.statuDetTrait) as TextView
                statuDetTrait.text = statuDetTrait.text.toString() + " En cours"
            }else{
                val validite = view?.findViewById<TextView>(R.id.statuDetTrait) as TextView
                statuDetTrait.text = statuDetTrait.text.toString() + " Expiré"
            }

            medicVM.getMedicTraitement(trait.idTraitement)
            medicVM.traitMeds.observe(requireActivity(), { traitMed ->
                for (it in traitMed) {
                    medicVM.getMedicById(it.idMedicament)
                    val listMed = mutableListOf<Medicament>()
                    medicVM.medic.observe(requireActivity(), { medic ->
                        listMed.add(medic)
                        if (it == traitMed.last()) {
                            rv.layoutManager = LinearLayoutManager(
                                requireActivity(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            rv.adapter = MedicamentAdapter(requireActivity(), listMed)
                        }
                    })
                }
            })
        })

        backButtonDetTrait.setOnClickListener{
            findNavController().navigate(R.id.action_detailsTraitementFragment_to_traitementsFragment)
        }
    }
}