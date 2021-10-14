package com.example.ejercicio8

data class Encuesta(var nombre:String,var so:String,var especialidades:ArrayList<String>,var horasEstudio:Int){
    override fun toString(): String {
        var cad="Encuesta($nombre, $so, ["
        for (e in especialidades){
            cad+= "$e, "
        }
        cad=cad.removeRange(cad.length-2 until cad.length)
        cad+="], $horasEstudio)"
        return cad
    }
}
