package com.example.juegodados

import android.util.Log
import android.widget.ImageView

class Hilo(var activity: MainActivity, var valores: Array<Int>) : Thread() {
    private var parar = false

    override fun run() {
        var seg = 0
        var dados = Array<ImageView?>(4) { null }
        dados[0] = activity.findViewById(R.id.imgD1J1)
        dados[1] = activity.findViewById(R.id.imgD2J1)
        dados[2] = activity.findViewById(R.id.imgD1J2)
        dados[3] = activity.findViewById(R.id.imgD2J2)

        while (seg < 20) {
            for (dado in dados.indices) {
                var numDado = (1..6).random()
                valores[dado] = numDado
                Log.i("prueba", "Llega aqui")
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
            Log.e("prueba: ", "Llega aqui(final)")
            sleep(100)
            seg++
        }
    }
}