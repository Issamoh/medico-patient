package com.example.medico.ui.conseil

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.medico.R
import com.example.medico.data.model.*
import com.example.medico.utils.sharedPrefFile
import de.hdodenhof.circleimageview.CircleImageView

class DemandeConseilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_demande_conseil, container, false)
        val backButtonConseil =  view.findViewById(R.id.backButtonConseil) as ImageButton
        backButtonConseil.setOnClickListener{
            val navController = (context as Activity).findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_demandeConseilFragment_to_detailsBookingFragment2)
        }
        val vmC=ViewModelProvider(requireActivity()).get(DemandeConseilViewModel::class.java)
        val docImgDetails = view.findViewById<CircleImageView>(R.id.docImgDetails)
        val nomDoctor = view.findViewById<TextView>(R.id.nomDoctor)
        val specDoctor = view.findViewById<TextView>(R.id.specDoctor)
        Glide.with(requireContext()).load(vmC.lienphoto).circleCrop().into(docImgDetails)
        nomDoctor.text =  vmC.nomMedecin
        specDoctor.text = vmC.specMedecin
        val conseilButton = view.findViewById<Button>(R.id.ConseilButton)
        val conseilContenu = view.findViewById<EditText>(R.id.contenuConseil)
        conseilButton.setOnClickListener {
            if(conseilContenu.text.isEmpty()){
                Toast.makeText(context, "Il faut introduire votre demande !", Toast.LENGTH_LONG).show()
            }else
            {
                val sharedPref = (context as Activity).getSharedPreferences(
                    sharedPrefFile, Context.MODE_PRIVATE
                )
                val id = sharedPref.getString("userID", "0")
                val  conseil = Conseil(id!!,vmC.idMedecin,conseilContenu.text.toString())
                RoomService.appDataBase.getConseilDao().addConseil(conseil)
                conseilContenu.text.clear()
                scheduleSycn()
            }
        }
        return view
    }
    private fun scheduleSycn() {
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).
            //    setRequiresBatteryNotLow(true).
        build()
        val req= OneTimeWorkRequest.Builder (SyncService::class.java).
        setConstraints(constraints).addTag("id1").
        build()
        val workManager = WorkManager.getInstance(this.context as Activity)
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)

    }
}