package com.example.e_checklist


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gabarito.*
import kotlinx.android.synthetic.main.activity_gabarito.btn_professor



class GabaritoActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gabarito)

        imageViewG.setImageBitmap(imgGlobal)

        btn_professor.setOnClickListener{
            textR = text
            preencherQuestionario()
            text = ""
            textR = ""

        }

        btn_aluno.setOnClickListener{
            nota()
        }
    }

    fun preencherQuestionario(){
        //Declarando as opções
        val A = "BCDE"
        val B = "ACDE"
        val C = "ABDE"
        val D = "ABCE"
        val E = "ABCD"

        //verificando as opções e armazenando em cada questão
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


        val P = Intent (this, ConfirmacaoProfessor::class.java)
        startActivity(P)



    }

    fun nota(){
        //Declarando as opções
        val A = "BCDE"
        val B = "ACDE"
        val C = "ABDE"
        val D = "ABCE"
        val E = "ABCD"


        //Verificando as opções e armazenando em cada questão
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

        var nota = 0
        var erros = 10

        //Comparando com o gabarito padrão para dar a nota final
        if(um == umP){
            nota+=1
            erros-=1
        }
        if(dois == doisP){
            nota+=1
            erros-=1
        }
        if(tres == tresP){
            nota+=1
            erros-=1
        }
        if(quatro == quatroP){
            nota+=1
            erros-=1
        }
        if(cinco == cincoP){
            nota+=1
            erros-=1
        }
        if(seis == seisP){
            nota+=1
            erros-=1
        }
        if(sete == seteP){
            nota+=1
            erros-=1
        }
        if(oito == oitoP){
            nota+=1
            erros-=1
        }
        if(nove == noveP){
            nota+=1
            erros-=1
        }
        if(dez == dezP){
            nota+=1
            erros-=1
        }

        NotaGlobal = nota
        ErrosGlobal = erros

        //exibindo a nota e linpamdo o texto capturado
        val R = Intent (this, Resultados::class.java)
        startActivity(R)

        text = ""
    }
}
