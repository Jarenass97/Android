package com.example.adivinanumero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.text.Editable
import android.widget.*
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    var numIntroducido: EditText? = null
    var botonIntentar: Button? = null
    var msg: TextView? = null
    val NUMERO_SECRETO: Int = (0..100).random()
    var intentos = 5
    var contadorVidas: TextView? = null
    var barraVidas: ProgressBar? = null
    var botonReiniciar: Button? = null
    var historial: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numIntroducido = findViewById(R.id.etNumIntroducido)
        botonIntentar = findViewById(R.id.btnIntentar)
        botonReiniciar = findViewById(R.id.btnReiniciar)
        contadorVidas = findViewById(R.id.txtVidas)
        barraVidas = findViewById(R.id.barVidas)
        msg = findViewById(R.id.txtMensaje)
        historial = findViewById(R.id.txtHistorial)

        historial!!.append("\n")
        barraVidas!!.max = intentos
        barraVidas!!.progress = barraVidas!!.max
        contadorVidas!!.text = "${getString(R.string.infoVidas)}: $intentos"
        botonIntentar!!.isEnabled = false
        numIntroducido!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (s != "") {
                    botonIntentar!!.isEnabled = numIntroducido!!.text.isNotEmpty()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })
    }


    fun intentar(view: View) {
        var num = numIntroducido!!.text.toString().toInt()
        if (num in 0..100) {
            if (intentos > 0) {
                intentos--
                barraVidas!!.progress = intentos
                contadorVidas!!.text = "${getString(R.string.infoVidas)}: $intentos"
                when {
                    num < NUMERO_SECRETO -> {
                        msg!!.text = getString(R.string.msgMenorQue)
                        Toast.makeText(this, getString(R.string.ToastMenor), Toast.LENGTH_SHORT)
                            .show()
                        historial!!.append("$num↓-")
                    }
                    num > NUMERO_SECRETO -> {
                        msg!!.text = getString(R.string.msgMayorQue)
                        Toast.makeText(this, getString(R.string.ToastMayor), Toast.LENGTH_SHORT)
                            .show()
                        historial!!.append("$num↑-")
                    }
                    else -> {
                        msg!!.text = "${getString(R.string.msgEncontrado)}: $NUMERO_SECRETO"
                        Toast.makeText(
                            this,
                            getString(R.string.ToastEncontrado),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        historial!!.append("$num*")
                        botonIntentar!!.isVisible = false
                        botonReiniciar!!.isVisible = true
                    }
                }
                if (intentos == 0) {
                    botonIntentar!!.isVisible = false
                    botonReiniciar!!.isVisible = true
                    msg!!.text=getString(R.string.msgSinIntentos)
                    var txt = historial!!.text
                    historial!!.text = txt.removeRange(txt.length - 1, txt.length)
                }
            }
        } else {
            msg!!.text = getString(R.string.msgOutOfRange)
        }
        numIntroducido!!.text.clear()
    }

    fun reiniciar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}