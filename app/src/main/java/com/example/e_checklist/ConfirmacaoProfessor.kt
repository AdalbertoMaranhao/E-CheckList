package com.example.e_checklist

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_confirmacao_professor.*
import kotlinx.android.synthetic.main.activity_resultados.*
import kotlinx.android.synthetic.main.activity_resultados.btn_professor
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ConfirmacaoProfessor : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacao_professor)
        btn_professor.setOnClickListener {
            dispatchCameraIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            try {
                val file = File(currentPath)
                val uri = Uri.fromFile(file)
                img = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                imgGlobal = img
                if (img != null) {
                    detectText(img)
                } else {
                    Toast.makeText(this, "imagem não capturada", Toast.LENGTH_SHORT)
                }

                val gabarito = Intent(this, GabaritoActivity::class.java)
                startActivity(gabarito)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (photoFile != null) {

                var photoUri = FileProvider.getUriForFile(
                    this,
                    "com.example.e_checklist.fileprovider", photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }


    fun createImage(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val imageName = "JPG_" + timestamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        currentPath = image.absolutePath
        return image
    }

    fun detectText(img: Bitmap?) {

        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(img!!)

        val firebaseVisionTextDetector = FirebaseVision.getInstance().onDeviceTextRecognizer

        firebaseVisionTextDetector.processImage(firebaseVisionImage).addOnSuccessListener { firebaseVisionText ->

            for (block in firebaseVisionText.textBlocks) {
                for (line in block.lines) {
                    for (element in line.elements) {
                        val elementT = element.text
                        text += elementT
                    }
                }
            }

        }
    }
}
