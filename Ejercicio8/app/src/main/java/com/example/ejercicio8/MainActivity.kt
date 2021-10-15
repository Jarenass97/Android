package com.example.ejercicio8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var etNombre: EditText? = null
    private var anonymus: Switch? = null
    private var radioMAC: RadioButton? = null
    private var radioWindows: RadioButton? = null
    private var radioLinux: RadioButton? = null
    private var ckbDam: CheckBox? = null
    private var ckbAsir: CheckBox? = null
    private var ckbDaw: CheckBox? = null
    private var barraHoras: SeekBar? = null
    private var textNumHoras: TextView? = null
    private var btnValidar: Button? = null
    private var btnReiniciar: Button? = null
    private var btnCuantas: Button? = null
    private var btnResumen: Button? = null
    private var resultado: TextView? = null

    var encuestasList: ArrayList<Encuesta> = ArrayList<Encuesta>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recogerVariables()
        iniciar()

        anonymus!!.setOnClickListener() {
            etNombre!!.isEnabled = if (anonymus!!.isChecked) {
                etNombre!!.setText(getString(R.string.switchAnonimo))
                false
            } else {
                etNombre!!.text.clear()
                true
            }
        }
        barraHoras!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textNumHoras?.text = barraHoras?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    fun recogerVariables() {
        etNombre = findViewById(R.id.etNombre)
        anonymus = findViewById(R.id.swchAnonimato)
        radioMAC = findViewById(R.id.rbMac)
        radioWindows = findViewById(R.id.rbWindows)
        radioLinux = findViewById(R.id.rbLinux)
        barraHoras = findViewById(R.id.sbHoras)
        textNumHoras = findViewById(R.id.txtNumHoras)
        ckbDam = findViewById(R.id.cbDAM)
        ckbAsir = findViewById(R.id.cbASIR)
        ckbDaw = findViewById(R.id.cbDAW)
        btnValidar = findViewById(R.id.btnValidar)
        btnReiniciar = findViewById(R.id.btnReiniciar)
        btnCuantas = findViewById(R.id.btnCuantas)
        btnResumen = findViewById(R.id.btnResumen)
        resultado = findViewById(R.id.txtResultado)
    }

    fun iniciar() {
        etNombre!!.text.clear()
        anonymus!!.isChecked = false
        radioMAC!!.isChecked = true
        ckbDam!!.isChecked = false
        ckbAsir!!.isChecked = false
        ckbDaw!!.isChecked = false
        barraHoras!!.progress = barraHoras!!.max / 2
        textNumHoras!!.text = (barraHoras!!.max / 2).toString()
    }

    fun validar(view: View) {
        var correcto = true
        var cad = ""
        if (etNombre!!.text.isEmpty()) {
            correcto = false
            cad += "Sin nombre\n"
        }
        if (!ckbDam!!.isChecked && !ckbAsir!!.isChecked && !ckbDaw!!.isChecked) {
            correcto = false
            cad += "No se ha seleccionado especialidad\n"
        }
        if (correcto) {
            var nombre = if (anonymus!!.isChecked) {
                getString(R.string.switchAnonimo)
            } else {
                etNombre!!.text.toString()
            }
            var groupSO: RadioGroup = findViewById(R.id.rgSO)
            var radioSO: RadioButton = findViewById(groupSO.checkedRadioButtonId)
            var so = radioSO.text.toString()
            var especialidades = ArrayList<String>(0)
            if (ckbDam!!.isChecked) especialidades.add(ckbDam!!.text.toString())
            if (ckbAsir!!.isChecked) especialidades.add(ckbAsir!!.text.toString())
            if (ckbDaw!!.isChecked) especialidades.add(ckbDaw!!.text.toString())
            var numHoras = textNumHoras!!.text.toString().toInt()
            encuestasList.add(Encuesta(nombre, so, especialidades, numHoras))
            cad = getString(R.string.validacionCorrecta)
            Toast.makeText(this, cad, Toast.LENGTH_SHORT).show()
            iniciar()
        }
        resultado!!.text = cad
    }

    fun reiniciar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        this.finish()
        startActivity(intent)
    }

    fun dimeCuantas(view: View) {
        resultado!!.text = "${getString(R.string.txtNumEncuestas)} ${encuestasList.size}"
    }

    fun resumen(view: View) {
        var resum: String = getString(R.string.tituloResumen) + "\n\n"
        for (e in encuestasList) {
            resum += "- $e\n"
        }
        resultado!!.text = resum
    }

}