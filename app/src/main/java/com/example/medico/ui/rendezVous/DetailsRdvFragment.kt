package com.example.medico.ui.rendezVous

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.medico.R
import com.example.medico.utils.sharedPrefFile

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


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
        val nomPatient = sharedPref.getString("nomUser", "0")
        val content = nomPatient
        //TODO jib les données mn vm.doctor
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
            (view.findViewById(R.id.qrImage) as ImageView).setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return  view
    }
}