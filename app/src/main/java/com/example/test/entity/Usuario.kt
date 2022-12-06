package com.example.test.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario {
    @PrimaryKey
    var correo: String
    var nombre: String? = null
    var apellido: String? = null
    var contrasena: String? = null

    constructor(correo: String, nombre: String?, apellido: String?, contrasena: String?) {
        this.correo = correo
        this.nombre = nombre
        this.apellido = apellido
        this.contrasena = contrasena
    }

    override fun toString(): String {
        return "Usuario(correo='$correo', nombre=$nombre, apellido=$apellido, contrasena=$contrasena)"
    }
}