package com.example.test

import android.util.Patterns
import java.util.regex.Pattern

class Validation {

    //Valida si texto esta vacio y elimina espacio.
    fun validarCampoNulo(texto:String):Boolean{
        return texto.trim().equals("") || texto.trim().length == 0
    }

    //Valida si texto segun una expresión regular
    fun validarNombre(nombre:String):Boolean{
        val pattern = Pattern.compile("^[a-zA-Z ]+\$")
        return !pattern.matcher(nombre).matches()
    }

    //Valida correcto formato del correo segun expresión regular
    fun validarFormatoCorreo(correo:String):Boolean{
        return !Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }
}