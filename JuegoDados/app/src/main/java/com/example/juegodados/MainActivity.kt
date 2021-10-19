package com.example.juegodados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    var puntosJ1 = 0
    var puntosJ2 = 0
    var ronda = 1
    var dados = Array<ImageView?>(4) { null }
    var valores = Array<Int>(4) { 0 }
    var resul: TextView? = null
    var botonJugar: Button? = null
    var botonReiniciar: Button? = null
    var marcador1: TextView? = null
    var marcador2: TextView? = null
    var hilo: Hilo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciar()
    }

    fun iniciar() {
        dados[0] = findViewById(R.id.imgD1J1)
        dados[1] = findViewById(R.id.imgD2J1)
        dados[2] = findViewById(R.id.imgD1J2)
        dados[3] = findViewById(R.id.imgD2J2)
        resul = findViewById(R.id.txtResultado)
        botonJugar = findViewById(R.id.btnJugar)
        botonReiniciar = findViewById(R.id.btnReiniciar)
        marcador1 = findViewById(R.id.txtPuntosJ1)
        marcador2 = findViewById(R.id.txtPuntosJ2)
    }

    fun juego(view: View) {
        val RONDAS = 5
        hilo = Hilo(this,valores)
        if (ronda <= RONDAS) {
            /*hilo!!.start()
            hilo!!.join()*/
            for (dado in dados.indices) {
                var numDado = (1..6).random()
                valores[dado] = numDado
                dados[dado]!!.setImageResource(
                    when (numDado) {
                        1 -> R.drawable.dado1
                        2 -> R.drawable.dado2
                        3 -> R.drawable.dado3
                        4 -> R.drawable.dado4
                        5 -> R.drawable.dado5
                        6 -> R.drawable.dado6
                        else -> R.mipmap.ic_launcher
                    }
                )
            }
            val TOTALJUG1 = valores[0] + valores[1]
            val TOTALJUG2 = valores[2] + valores[3]
            puntosJ1 += TOTALJUG1
            puntosJ2 += TOTALJUG2
            marcador1!!.text = "[$puntosJ1]"
            marcador2!!.text = "[$puntosJ2]"
            if (ronda == RONDAS) {
                resul!!.text = when {
                    puntosJ1 > puntosJ2 -> getString(R.string.resulWin)
                    puntosJ1 < puntosJ2 -> getString(R.string.resulLose)
                    else -> getString(R.string.resulEmp)
                }
                botonJugar!!.isVisible = false
                botonReiniciar!!.isVisible = true
            } else {
                resul!!.text = "${getString(R.string.ronda)} $ronda/$RONDAS"
            }
            ronda++
        }
    }

    fun reiniciar(view: View) {
        dados[0]!!.setImageResource(R.drawable.dado1)
        dados[1]!!.setImageResource(R.drawable.dado6)
        dados[2]!!.setImageResource(R.drawable.dado6)
        dados[3]!!.setImageResource(R.drawable.dado1)
        puntosJ1 = 0
        puntosJ2 = 0
        ronda = 1
        marcador1!!.text = "[$puntosJ1]"
        marcador2!!.text = "[$puntosJ2]"
        resul!!.text = getString(R.string.resultadoDef)
        botonReiniciar!!.isVisible = false
        botonJugar!!.isVisible = true
    }
}