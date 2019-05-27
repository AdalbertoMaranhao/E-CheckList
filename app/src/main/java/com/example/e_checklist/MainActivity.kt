package com.example.e_checklist


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var currentPath: String? = null
    val TAKE_PICTURE = 1
    var img: Bitmap? = BitmapFactory.decodeFile(currentPath)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_capturar.setOnClickListener {
            dispatchCameraIntent()
        }
        btn_gabarito.setOnClickListener{
            //val pdf_url = "https://firebasestorage.googleapis.com/v0/b/e-checklis.appspot.com/o/gabarito_padrao.png?alt=media&token=f3093479-3cfa-4626-8c3c-365b380bfe81"
            //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url))
            //startActivity(browserIntent)
            detectText(img)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
            try {
                val file = File(currentPath)
                val uri = Uri.fromFile(file)
                img = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                imageView.setImageBitmap(img)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    fun dispatchCameraIntent(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null){
            var photoFile: File? = null
            try {
                photoFile = createImage()
            }catch (e: IOException){
                e.printStackTrace()
            }
            if (photoFile != null){

                var photoUri = FileProvider.getUriForFile(this,
                    "com.example.e_checklist.fileprovider", photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }


    fun createImage(): File{
        val timestamp = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val imageName = "JPG_"+timestamp+"_"
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
                val text = block.text
                textDetect.setText(text)
            }

        }

    }

}
