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
    var text = ""
    var textR = ""

    var umP = ""
    var doisP = ""
    var tresP = ""
    var quatroP = ""
    var cincoP = ""
    var seisP = ""
    var seteP = ""
    var oitoP = ""
    var noveP = ""
    var dezP = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_capturar.setOnClickListener {
            dispatchCameraIntent()

        }
        btn_professor.setOnClickListener{
           // detectText(img)
            textR = text
            preencherQuestionario()
            text = ""
            textR = ""

        }
        btn_notas.setOnClickListener{
            nota()
        }
        btn_gabarito.setOnClickListener{
            //val pdf_url = "https://firebasestorage.googleapis.com/v0/b/e-checklis.appspot.com/o/gabarito_padrao.png?alt=media&token=f3093479-3cfa-4626-8c3c-365b380bfe81"
            //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url))
            //startActivity(browserIntent)

            detectText(img)


            /*text = "GABARITO1ABCD6ABCD2ABCD7BCDE3ABCD8ABCD4ABCD9ABCD5ABCD10ABCD"


            if(text.toLowerCase().contains("7bcde")){
                textDetect.text = "A"
            }
            if(text.toLowerCase().contains("7acde")){
                textDetect.text = "B"
            }
            if(text.toLowerCase().contains("7abde")){
                textDetect.text = "C"
            }
            if(text.toLowerCase().contains("7abce")){
                textDetect.text = "D"
            }
            if(text.toLowerCase().contains("7abcd")){
                textDetect.text = "E"
            }

        */


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
                //val blockT = block.text
                //text += blockT
                for (line in block.lines) {
                  //  val lineT = line.text
                    //text += lineT
                   for (element in line.elements) {
                     val elementT = element.text
                       text += elementT
                    }
                }
            }

        }
    }

    fun preencherQuestionario(){
        val A = "BCDE"
        val B = "ACDE"
        val C = "ABDE"
        val D = "ABCE"
        val E = "ABCD"
        /* //val G = "GABARITO"
         //val L = "ABCDE"
         //val N= "12345678910"
         //var resp = ""//"GABARITO1ABCDE6ABCDE2ABCDE7ABCDE3ABCDE8ABCDE4ABCDE9ABCDE5ABCDE10ABCDE"
             //resp = G+N.get(0)+L+N.get(5)+L+N.get(1)+L+N.get(6)+L+N.get(2)+L+N.get(7)+L+N.get(3)+L+N.get(8)+L+N.get(4)+L+N.get(9)
         */

        //textR = "GABARITO1ABCD6ABCD2ABCD7ABCD3ABCD8ABCD4ABCD9ABCD5ABCD10ABCD"
       // val textD = text.substring(53,59)
       // text = text.substring(8, 59)


       // val resp = text.chunked(5)



        umP = when{
            textR.contains("1$A") -> "a"
            textR.contains("1$B") -> "b"
            textR.contains("1$C") -> "c"
            textR.contains("1$D") -> "d"
            textR.contains("1$E") -> "e"
            else -> "Erro"
        }
        doisP = when{
            textR.contains("2$A") -> "a"
            textR.contains("2$B") -> "b"
            textR.contains("2$C") -> "c"
            textR.contains("2$D") -> "d"
            textR.contains("2$E") -> "e"
            else -> "Erro"
        }
        tresP = when{
            textR.contains("3$A") -> "a"
            textR.contains("3$B") -> "b"
            textR.contains("3$C") -> "c"
            textR.contains("3$D") -> "d"
            textR.contains("3$E") -> "e"
            else -> "Erro"
        }
        quatroP = when{
            textR.contains("4$A") -> "a"
            textR.contains("4$B") -> "b"
            textR.contains("4$C") -> "c"
            textR.contains("4$D") -> "d"
            textR.contains("4$E") -> "e"
            else -> "Erro"
        }
        cincoP = when{
            textR.contains("5$A") -> "a"
            textR.contains("5$B") -> "b"
            textR.contains("5$C") -> "c"
            textR.contains("5$D") -> "d"
            textR.contains("5$E") -> "e"
            else -> "Erro"
        }
        seisP = when{
            textR.contains("6$A") -> "a"
            textR.contains("6$B") -> "b"
            textR.contains("6$C") -> "c"
            textR.contains("6$D") -> "d"
            textR.contains("6$E") -> "e"
            else -> "Erro"
        }
        seteP = when{
            textR.contains("7$A") -> "a"
            textR.contains("7$B") -> "b"
            textR.contains("7$C") -> "c"
            textR.contains("7$D") -> "d"
            textR.contains("7$E") -> "e"
            else -> "Erro"
        }
        oitoP = when{
            textR.contains("8$A") -> "a"
            textR.contains("8$B") -> "b"
            textR.contains("8$C") -> "c"
            textR.contains("8$D") -> "d"
            textR.contains("8$E") -> "e"
            else -> "Erro"
        }
        noveP = when{
            textR.contains("9$A") -> "a"
            textR.contains("9$B") -> "b"
            textR.contains("9$C") -> "c"
            textR.contains("9$D") -> "d"
            textR.contains("9$E") -> "e"
            else -> "Erro"
        }
        dezP = when{
            textR.contains("10$A") -> "a"
            textR.contains("10$B") -> "b"
            textR.contains("10$C") -> "c"
            textR.contains("10$D") -> "d"
            textR.contains("10$E") -> "e"
            else -> "Erro"
        }

    }

    fun nota(){

        val A = "BCDE"
        val B = "ACDE"
        val C = "ABDE"
        val D = "ABCE"
        val E = "ABCD"

        //text = "GABARITO1ABCD6ABCE2BCDE7ABCD3ABCD8ABCD4ABCD9ABCD5ABCD10ABCD"
       // val textD = text.substring(53,59)
       // text = text.substring(8, 59)


       // val resp = text.chunked(5)

        val um = when{
            text.contains("1$A") -> "a"
            text.contains("1$B") -> "b"
            text.contains("1$C") -> "c"
            text.contains("1$D") -> "d"
            text.contains("1$E") -> "e"
            else -> "Erro"
        }
        val dois = when{
            text.contains("2$A") -> "a"
            text.contains("2$B") -> "b"
            text.contains("2$C") -> "c"
            text.contains("2$D") -> "d"
            text.contains("2$E") -> "e"
            else -> "Erro"
        }
        val tres = when{
            text.contains("3$A") -> "a"
            text.contains("3$B") -> "b"
            text.contains("3$C") -> "c"
            text.contains("3$D") -> "d"
            text.contains("3$E") -> "e"
            else -> "Erro"
        }
        val quatro = when{
            text.contains("4$A") -> "a"
            text.contains("4$B") -> "b"
            text.contains("4$C") -> "c"
            text.contains("4$D") -> "d"
            text.contains("4$E") -> "e"
            else -> "Erro"
        }
        val cinco = when{
            text.contains("5$A") -> "a"
            text.contains("5$B") -> "b"
            text.contains("5$C") -> "c"
            text.contains("5$D") -> "d"
            text.contains("5$E") -> "e"
            else -> "Erro"
        }
        val seis = when{
            text.contains("6$A") -> "a"
            text.contains("6$B") -> "b"
            text.contains("6$C") -> "c"
            text.contains("6$D") -> "d"
            text.contains("6$E") -> "e"
            else -> "Erro"
        }
        val sete = when{
            text.contains("7$A") -> "a"
            text.contains("7$B") -> "b"
            text.contains("7$C") -> "c"
            text.contains("7$D") -> "d"
            text.contains("7$E") -> "e"
            else -> "Erro"
        }
        val oito = when{
            text.contains("8$A") -> "a"
            text.contains("8$B") -> "b"
            text.contains("8$C") -> "c"
            text.contains("8$D") -> "d"
            text.contains("8$E") -> "e"
            else -> "Erro"
        }
        val nove = when{
            text.contains("9$A") -> "a"
            text.contains("9$B") -> "b"
            text.contains("9$C") -> "c"
            text.contains("9$D") -> "d"
            text.contains("9$E") -> "e"
            else -> "Erro"
        }
        val dez = when{
            text.contains("10$A") -> "a"
            text.contains("10$B") -> "b"
            text.contains("10$C") -> "c"
            text.contains("10$D") -> "d"
            text.contains("10$E") -> "e"
            else -> "Erro"
        }

        var nota= 0

        if(um == umP){
            nota+=1
        }
        if(dois == doisP){
            nota+=1
        }
        if(tres == tresP){
            nota+=1
        }
        if(quatro == quatroP){
            nota+=1
        }
        if(cinco == cincoP){
            nota+=1
        }
        if(seis == seisP){
            nota+=1
        }
        if(sete == seteP){
            nota+=1
        }
        if(oito == oitoP){
            nota+=1
        }
        if(nove == noveP){
            nota+=1
        }
        if(dez == dezP){
            nota+=1
        }

        textDetect.text = text +" - - - "+ nota.toString()
        text = ""
    }

}
