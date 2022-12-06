package com.example.test.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test.entity.Usuario

@Dao
interface DaoUsuario {
    @Query("SELECT * FROM Usuario")
    //METODO APRA OBTENER LA INFO
    //suspend son las corrutinas estas son una de las características más impresionantes de Kotlin is simply a function that can be paused and resumed
    suspend fun obtenerUsuarios():List<Usuario>

    @Query("SELECT * FROM Usuario WHERE correo=:correo")
    suspend fun obtenerUsuario(correo: String): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE correo=:correo AND contrasena=:contrasena")
    suspend fun login(correo: String, contrasena: String): List<Usuario>

    @Insert
    suspend fun agregarUsuario(correo: Usuario):Long

    //=: nos permite parametrizar
    @Query("UPDATE  Usuario SET nombre=:nombre,apellido=:apellido,contrasena=:contrasena WHERE correo=:correo")
    suspend fun actualizarUsuario(nombre:String,apellido:String,correo: String,contrasena:String): Int

    @Query("DELETE FROM Usuario WHERE correo=:correo")
    suspend fun borrarUsuario(correo: String)
}