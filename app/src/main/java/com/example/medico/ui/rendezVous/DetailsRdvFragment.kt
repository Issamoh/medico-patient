package com.example.medico.ui.rendezVous

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.medico.R
import com.example.medico.data.model.DetailsRdvViewModel
import com.example.medico.utils.sharedPrefFile

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import kotlinx.android.synthetic.main.fragment_details_rdv.*


class DetailsRdvFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_details_rdv, container, false)
        val sharedPref = (context as Activity).getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )
        val nomPatientV = sharedPref.getString("nomUser", "0")
        val vmDetails= ViewModelProvider(requireActivity()).get(DetailsRdvViewModel::class.java)
        val nomDoctorX = (view.findViewById(R.id.nomDoctorX) as TextView)
        val specDoctorX = (view.findViewById(R.id.specDoctorX) as TextView)
        val prixDoctorX = (view.findViewById(R.id.prixDoctorX) as TextView)
        val nomPatientX = (view.findViewById(R.id.nomPatientX) as TextView)
        val dateHeureX = (view.findViewById(R.id.dateHeureX) as TextView)
        nomDoctorX.text = vmDetails.nomMedecin
        specDoctorX.text = vmDetails.specMedecin
        prixDoctorX.text = vmDetails.prix
        nomPatientX.text = nomPatientV
        dateHeureX.text = vmDetails.date+" à "+vmDetails.heure
        val content = "{'nomPatient' : '"+nomPatientV+"','nomMedecin' : '" +vmDetails.nomMedecin+"', 'date' : '"+vmDetails.date+"', 'heure' : '"+vmDetails.heure+"', 'spec' : '"+vmDetails.specMedecin+"', 'prix' : '"+vmDetails.prix+"'}"
        val writer = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width: Int = bitMatrix.getWidth()
            val height: Int = bitMatrix.getHeight()
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            (view.findViewById(R.id.qrImageX) as ImageView).setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }

       val backButtonBooking =  view.findViewById(R.id.backButtonBooking) as ImageButton
        backButtonBooking.setOnClickListener{
            val navController = (context as Activity).findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_detailsRdvFragment2_to_mesRdvFragment)
        }
        return  view
    }
}