package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class Servicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)

        //MS = menu_Servicio

        val btn_rollbackServicio = findViewById<ImageButton>(R.id.btn_rollbackServicio)
        val btn_editarReservaS = findViewById<Button>(R.id.btn_register)

        //Boton volver
        btn_rollbackServicio.setOnClickListener {
            val intent = Intent(this@Servicio,Reserva::class.java)
            startActivity(intent)
        }

        //Boton editar reservar
        btn_editarReservaS.setOnClickListener {
            val intent = Intent(this@Servicio,Reserva::class.java)
            Toast.makeText(this, "Reserva editada con exito", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}

