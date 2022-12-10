package com.example.test.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Reservas {
    @PrimaryKey(autoGenerate = true)

    var id: Long = 0
    var local: String? = null
    var fecha: String? = null
    var hora: String? = null
    var servicio: String? = null
    var user: String? = null

    constructor(local: String?, fecha: String?, hora: String?, servicio: String?, user: String?) {
        this.local = local
        this.fecha = fecha
        this.hora = hora
        this.servicio = servicio
        this.user = user
    }

    override fun toString(): String {
        return "Reservas(id=$id, local=$local, fecha=$fecha, hora=$hora, servicio=$servicio, user=$user)"
    }
}