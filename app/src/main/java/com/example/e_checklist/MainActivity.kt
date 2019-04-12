package com.example.e_checklist

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_preencher.setOnClickListener {
            val intent = Intent(this, GabaritoActivity::class.java)

            startActivity(intent)
        }
        btn_gabarito.setOnClickListener{
            val pdf_url = "http://oab.fgv.br/GABARITOS_RETIFICADOS_XXVIII_EXAME_DE_ORDEM.pdf"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url))
            startActivity(browserIntent)
        }
    }
}
