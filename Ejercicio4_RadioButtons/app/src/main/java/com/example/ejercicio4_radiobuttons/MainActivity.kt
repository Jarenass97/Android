package com.example.ejercicio4_radiobuttons


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

        var botonSumar: Button = findViewById(R.id.btnSuma)
        var botonRestar: Button = findViewById(R.id.btnResta)
        var botonMultiplicar: Button = findViewById(R.id.btnMultiplica)
        var botonDividir: Button = findViewById(R.id.btnDivide)

        var cajaNum1: EditText = findViewById(R.id.edNum1)
        var cajaNum2: EditText = findViewById(R.id.edNum2)
        var resul: TextView = findViewById(R.id.txtResultado)


        var res = 0

        botonSumar.setOnClickListener() {
            var n1 = Integer.parseInt(cajaNum1.text.toString())
            var n2 = Integer.parseInt(cajaNum2.text.toString())
            res = operacion(n1, n2, 0)
            resul.text = "${getString(R.string.resultado)} $res"
            Toast.makeText(this, getText(R.string.toastSum), Toast.LENGTH_SHORT).show()
        }
        botonRestar.setOnClickListener() {
            var n1 = Integer.parseInt(cajaNum1.text.toString())
            var n2 = Integer.parseInt(cajaNum2.text.toString())
            res = operacion(n1, n2, 1)
            resul.text = "${getString(R.string.resultado)} $res"
            Toast.makeText(this, getText(R.string.toastRes), Toast.LENGTH_SHORT).show()
        }
        botonMultiplicar.setOnClickListener() {
            var n1 = Integer.parseInt(cajaNum1.text.toString())
            var n2 = Integer.parseInt(cajaNum2.text.toString())
            res = operacion(n1, n2, 2)
            resul.text = "${getString(R.string.resultado)} $res"
            Toast.makeText(this, getText(R.string.toastMul), Toast.LENGTH_SHORT).show()
        }
        botonDividir.setOnClickListener() {
            var n1 = Integer.parseInt(cajaNum1.text.toString())
            var n2 = Integer.parseInt(cajaNum2.text.toString())
            res = operacion(n1, n2, 3)
            resul.text = "${getString(R.string.resultado)} $res"
            Toast.makeText(this, getText(R.string.toastDiv), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *  0. Suma
     *  1. Resta
     *  2. Multiplica
     *  3. Divide
     */
    private fun operacion(n1: Int, n2: Int, operacion: Int): Int {
        var res = 0
        when (operacion) {
            0 -> res = n1 + n2
            1 -> res = n1 - n2
            2 -> res = n1 * n2
            3 -> if (n2 != 0) res = n1 / n2
        }
        return res
    }


}