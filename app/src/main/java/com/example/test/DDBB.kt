package com.example.test

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.dao.DaoReservas
import com.example.test.dao.DaoUsuario
import com.example.test.entity.Reservas
import com.example.test.entity.Usuario

@Database(
    entities = [Usuario::class,Reservas::class],
    version = 1
)

abstract class DDBB:RoomDatabase(){
    abstract fun daoUsuario(): DaoUsuario
    abstract fun daoReserva():DaoReservas
}