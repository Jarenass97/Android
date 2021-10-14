package com.example.ejercicio8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    /*var etNombre:EditText=findViewById(R.id.etNombre)
    var anonymus:Switch=findViewById(R.id.swchAnonimato)
    var radioMAC:RadioButton=findViewById(R.id.rbMac)
    var radioWindows:RadioButton=findViewById(R.id.rbWindows)
    var radioLinux:RadioButton=findViewById(R.id.rbLinux)
    var ckbDam:CheckBox=findViewById(R.id.cbDAM)
    var ckbAsir:CheckBox=findViewById(R.id.cbASIR)
    var ckbDaw:CheckBox=findViewById(R.id.cbDAW)
    var barraHoras:SeekBar=findViewById(R.id.sbHoras)
    var textNumHoras:TextView=findViewById(R.id.txtNumHoras)
    var btnValidar:Button=findViewById(R.id.btnValidar)
    var btnReiniciar:Button=findViewById(R.id.btnReiniciar)
    var btnCuantas:Button=findViewById(R.id.btnCuantas)
    var btnResumen:Button=findViewById(R.id.btnResumen)
    var resultado:TextView=findViewById(R.id.txtResultado)*/

    var encuestasList:ArrayList<Encuesta> =ArrayList<Encuesta>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciar()

        var barraHoras:SeekBar=findViewById(R.id.sbHoras)
        barraHoras.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var textNumHoras:TextView=findViewById(R.id.txtNumHoras)
                textNumHoras.text=barraHoras.progress.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    fun iniciar(){
        var etNombre:EditText=findViewById(R.id.etNombre)
        var anonymus:Switch=findViewById(R.id.swchAnonimato)
        var radioMAC:RadioButton=findViewById(R.id.rbMac)
        var barraHoras:SeekBar=findViewById(R.id.sbHoras)
        var textNumHoras:TextView=findViewById(R.id.txtNumHoras)
        var ckbDam:CheckBox=findViewById(R.id.cbDAM)
        var ckbAsir:CheckBox=findViewById(R.id.cbASIR)
        var ckbDaw:CheckBox=findViewById(R.id.cbDAW)
        etNombre.text.clear()
        anonymus.isChecked=false
        radioMAC.isChecked=true
        ckbDam.isChecked=false
        ckbAsir.isChecked=false
        ckbDaw.isChecked=false
        barraHoras.progress=barraHoras.max/2
        textNumHoras.text=(barraHoras.max/2).toString()
    }

    fun validar(view: View){
        var nombre:String
        var anonymus:Switch=findViewById(R.id.swchAnonimato)
        nombre = if(anonymus.isChecked){
            getString(R.string.switchAnonimo)
        } else {
            var etNombre:EditText=findViewById(R.id.etNombre)
            etNombre.text.toString()
        }
        var groupSO:RadioGroup=findViewById(R.id.rgSO)
        var radioSO:RadioButton=findViewById(groupSO.checkedRadioButtonId)
        var so=radioSO.text.toString()
        var especialidades=ArrayList<String>(0)
        var ckbDam:CheckBox=findViewById(R.id.cbDAM)
        if (ckbDam.isChecked)especialidades.add(ckbDam.text.toString())
        var ckbAsir:CheckBox=findViewById(R.id.cbASIR)
        if (ckbAsir.isChecked)especialidades.add(ckbAsir.text.toString())
        var ckbDaw:CheckBox=findViewById(R.id.cbDAW)
        if (ckbDaw.isChecked)especialidades.add(ckbDaw.text.toString())
        var textNumHoras:TextView=findViewById(R.id.txtNumHoras)
        var numHoras=textNumHoras.text.toString().toInt()
        encuestasList.add(Encuesta(nombre,so,especialidades,numHoras))
        Toast.makeText(this,getString(R.string.validacionCorrecta),Toast.LENGTH_SHORT).show()
        iniciar()
    }

    fun reiniciar(view:View){
        val intent = Intent(this, MainActivity::class.java)
        this.finish()
        startActivity(intent)
    }
    fun dimeCuantas(view: View){
        var resultado:TextView=findViewById(R.id.txtResultado)
        resultado.text="${getString(R.string.txtNumEncuestas)} ${encuestasList.size}"
    }
    fun resumen(view: View){
        var resultado:TextView=findViewById(R.id.txtResultado)
        var resum:String=getString(R.string.tituloResumen)+"\n\n"
        for(e in encuestasList){
            resum+= "- $e\n"
        }
        resultado.text=resum
    }

}