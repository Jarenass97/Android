package com.example.ejercicio3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton: Button =findViewById(R.id.btnPulsame)
        var cajaNum1: EditText =findViewById(R.id.edNum1)
        var cajaNum2: EditText =findViewById(R.id.edNum2)
        var resul: TextView =findViewById(R.id.txtResultado)

        boton.setOnClickListener(){
            var n1=Integer.parseInt(cajaNum1.text.toString())
            var n2=Integer.parseInt(cajaNum2.text.toString())
            var sum=n1+n2
            resul.text= "${getString(R.string.resultado)} $sum"
            Toast.makeText(this,getText(R.string.toast), Toast.LENGTH_SHORT).show()
        }
    }
}