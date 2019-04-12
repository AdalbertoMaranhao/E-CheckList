package com.example.e_checklist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gabarito.*

class GabaritoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gabarito)

        btn_salvar.setOnClickListener {
            Toast.makeText(applicationContext,"Salvo com sucesso",Toast.LENGTH_SHORT ).show()
            finish()
        }
        btn_cancelar.setOnClickListener {
            finish()
        }
    }
}
