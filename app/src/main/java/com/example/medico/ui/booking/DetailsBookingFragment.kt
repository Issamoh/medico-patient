package com.example.medico.ui.booking

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medico.R
import com.example.medico.data.model.*
import com.example.medico.data.repositories.RdvRepository
import com.example.medico.ui.home.MedecinViewModel
import com.example.medico.utils.sharedPrefFile
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_details_booking.*
import org.w3c.dom.Text
import java.lang.String
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*


class DetailsBookingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_booking, container, false)

        //On vérifie que les données sont bien passées via le viewModel
        val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        medVM.currentMed.observe(requireActivity(), { med ->
            val nD = view.findViewById(R.id.nomDoctor) as TextView
            nD.setText("Pr. " + med.nomMedecin + " " + med.prenomMedecin)
            val specDoctor = view.findViewById(R.id.specDoctor) as TextView
            specDoctor.text = med.idSpecialite.toString()
            val noteDoctor = view.findViewById(R.id.noteDoctor) as TextView
            noteDoctor.text = med.noteMedecin.toString()
            val photoMedecin = view.findViewById(R.id.docImgDetails) as CircleImageView
            Glide.with(requireContext()).load(med.photoMedecin).circleCrop().into(photoMedecin)
        })
        val backButtonBooking = view.findViewById(R.id.backButtonBooking) as ImageButton
        backButtonBooking.setOnClickListener{
            val navController = this.findNavController()
            navController.navigate(R.id.action_detailsBookingFragment2_to_homepageFragment)
        }

        val vm=ViewModelProvider(requireActivity()).get(RdvViewModel::class.java)

        val df = SimpleDateFormat("dd/MM/yyyy")
        val cal = Calendar.getInstance()
        val todayIndex = cal[Calendar.DAY_OF_MONTH]
        val thisMonthIndex = cal[Calendar.MONTH] //starts at 0
        val thisYearIndex = cal[Calendar.YEAR]
        vm.annee=thisYearIndex
        vm.mois=thisMonthIndex+1
        vm.jour=todayIndex
        val mois = arrayOf(
            "Janvier",
            "Féverier",
            "Mars",
            "Avril",
            "Mai",
            "Juin",
            "Juillet",
            "Août",
            "Septembre",
            "Octobre",
            "Novembre",
            "Décembre"
        )
        val dateTextView = view.findViewById<TextView>(R.id.textView2)
        dateTextView.text = mois[vm.mois - 1]+" "+vm.annee


        val  recyclerView = view.findViewById<RecyclerView>(R.id.days_list)
        recyclerView.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = daysAdapter(
            view.context, loadData(
                todayIndex,
                thisMonthIndex + 1,
                thisYearIndex
            )
        )

        val  recyclerViewMatin = view.findViewById<RecyclerView>(R.id.matin_list)
        recyclerViewMatin.layoutManager = GridLayoutManager(view.context, 4)
        recyclerViewMatin.adapter = HoursAdapter(view.context, loadMatinHours())

        val  recyclerViewSoir = view.findViewById<RecyclerView>(R.id.soir_list)
        recyclerViewSoir.layoutManager = GridLayoutManager(view.context, 4)
        recyclerViewSoir.adapter = HoursAdapter(view.context, loadSoirHours())




        val nextMonth = view.findViewById<ImageButton>(R.id.next_month)
        nextMonth.setOnClickListener {
            if (vm.mois == 12) {
                vm.annee++
                vm.mois=1

            }
            else
            {
                vm.mois++
            }
            if (vm.mois == thisMonthIndex+1 && vm.annee == thisYearIndex) {
            recyclerView.adapter = daysAdapter(
                view.context, loadData(
                    todayIndex,
                    thisMonthIndex + 1,
                    thisYearIndex
                )
            )
        }
            else{
            recyclerView.adapter = daysAdapter(view.context, loadData(1, vm.mois, vm.annee))
        }
            dateTextView.text = mois[vm.mois - 1]+" "+vm.annee
        }
        val previousMonth = view.findViewById<ImageButton>(R.id.previous_month)
        previousMonth.setOnClickListener {
            if (vm.mois != thisMonthIndex+1 || vm.annee != thisYearIndex) {
                if(vm.mois==1){
                    vm.annee--
                    vm.mois=12
                }else{
                    vm.mois--
                }
            }
            if (vm.mois == thisMonthIndex+1 && vm.annee == thisYearIndex) {
                recyclerView.adapter = daysAdapter(
                    view.context, loadData(
                        todayIndex,
                        thisMonthIndex + 1,
                        thisYearIndex
                    )
                )
            }
            else{
                recyclerView.adapter = daysAdapter(view.context, loadData(1, vm.mois, vm.annee))
            }
            dateTextView.text = mois[vm.mois - 1]+" "+vm.annee
        }

        val bookButton = view.findViewById<Button>(R.id.BookButton)
        bookButton.setOnClickListener {
            if (vm.annee == -1 || vm.mois == -1 || vm.jour == -1 || vm.heure == "00") {
                Toast.makeText(
                    context,
                    "Il faut choisir une date et une heure !",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val date = String.format("%d-%d-%d", vm.annee, vm.mois, vm.jour)

                val sharedPref = (context as Activity).getSharedPreferences(
                    sharedPrefFile, Context.MODE_PRIVATE
                )
                val id = sharedPref.getString("userID", "0")
                //TODO pass also doctor as param to show his infos in rdv details
                val vmDetails=ViewModelProvider(requireActivity()).get(DetailsRdvViewModel::class.java)
                medVM.currentMed.observe(requireActivity(), { med ->
                    vmDetails.date = date
                    vmDetails.heure = vm.heure
                    vmDetails.nomMedecin = "Pr. "+med.nomMedecin+" "+med.prenomMedecin
                    vmDetails.prix = "2000 DA"
                    vmDetails.specMedecin = med.idSpecialite.toString()
                })
                RdvRepository.prendreRdv(
                    context as Activity, DemandeRdv(
                        id!!.toInt(),
                        1,
                        date,
                        vm.heure
                    )
                )
            }
        }
        val callDoctor = view.findViewById<ImageView>(R.id.callDoctor)
        callDoctor.setOnClickListener {
            val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
            medVM.currentMed.observe(requireActivity(), { med ->
                val uri = Uri.parse("tel:"+med.telephoneMedecin)
                val intent = Intent(Intent.ACTION_DIAL, uri)
                if (intent.resolveActivity((context as Activity).packageManager) != null) {
                    (context as Activity).startActivity(intent)
                }
            })
        }

        val positionDoctor = view.findViewById<ImageView>(R.id.positionDoctor)
        positionDoctor.setOnClickListener {
            val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
            medVM.currentMed.observe(requireActivity(), { med ->
                val longitude = med.cabinetMedecinLongitude
                val latitude = med.cabinetMedecinLatitude
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=$latitude,$longitude")
                )
                (context as Activity).startActivity(intent)
            })
        }
        val askDoctor = view.findViewById(R.id.askDoctor) as ImageView
        askDoctor.setOnClickListener{
            val vmC=ViewModelProvider(requireActivity()).get(DemandeConseilViewModel::class.java)
            val medVM = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
            medVM.currentMed.observe(requireActivity(), { med ->
                vmC.idMedecin = med.idMedecin.toString()
                vmC.lienphoto = med.photoMedecin
                vmC.nomMedecin = "Pr. "+med.nomMedecin+" "+med.prenomMedecin
                vmC.specMedecin = med.idSpecialite.toString()
            })
            val navController = (context as Activity).findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_detailsBookingFragment2_to_demandeConseilFragment)
        }


        return view

    }

    private fun loadMatinHours(): List<Hour> {
        val data = mutableListOf<Hour>()
        data.add(Hour("8:00"))
        data.add(Hour("8:30"))
        data.add(Hour("9:00"))
        data.add(Hour("9:30"))
        data.add(Hour("10:00"))
        data.add(Hour("10:30"))
        data.add(Hour("11:00"))
        data.add(Hour("11:30"))

        return data

    }

    private fun loadSoirHours(): List<Hour> {
        val data = mutableListOf<Hour>()
        data.add(Hour("13:00"))
        data.add(Hour("13:30"))
        data.add(Hour("14:00"))
        data.add(Hour("14:30"))
        data.add(Hour("15:00"))
        data.add(Hour("15:30"))
        data.add(Hour("16:00"))
        data.add(Hour("16:30"))
        return data

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadData(indexDay: Int, indexMonth: Int, year: Int): List<Day> {
        val data = mutableListOf<Day>()
        val yearMonthObject: YearMonth = YearMonth.of(year, indexMonth)
        val daysInMonth: Int = yearMonthObject.lengthOfMonth()
        for (i in indexDay..daysInMonth){
            val dateString = String.format("%d-%d-%d", year, indexMonth, i)
            val date = SimpleDateFormat("yyyy-M-d").parse(dateString)
            val dayOfWeek = SimpleDateFormat("EEEE", Locale.FRANCE).format(date)
            data.add(Day(dayOfWeek, i))
        }
        return data
    }

}