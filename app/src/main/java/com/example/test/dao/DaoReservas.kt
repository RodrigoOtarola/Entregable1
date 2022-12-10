package com.example.test.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test.entity.Reservas

@Dao
interface DaoReservas {
    @Query("SELECT * FROM Reservas")
    //METODO APRA OBTENER LA INFO
    //suspend son las corrutinas estas son una de las características más impresionantes de Kotlin is simply a function that can be paused and resumed
    suspend fun obtenerReserva(): List<Reservas>

    @Query("SELECT * FROM Reservas WHERE user=:user")
    suspend fun obtenerReservasUsuario(user: String): List<Reservas>

    @Query("SELECT * FROM Reservas WHERE servicio=:servicio AND user=:user")
    suspend fun obtenerReservasPorServicio(servicio: String,user: String): List<Reservas>

    @Insert
    suspend fun agregarReserva(coctel: Reservas):Long

    @Query("UPDATE  Reservas SET local=:local,fecha=:fecha,hora=:hora,servicio=:servicio WHERE id=:id")
    suspend fun actualizarReserva(local:String,fecha: String,hora:String,servicio:String,id:Long): Int

    @Query("DELETE FROM Reservas WHERE id=:id")
    suspend fun borrarReserva(id: Long)
}