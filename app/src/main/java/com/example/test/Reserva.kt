package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        //REFERENCIAS
        val btn_editarReserva = findViewById<ImageButton>(R.id.btn_editarReserva)
        val btn_eliminarReserva = findViewById<ImageButton>(R.id.btn_eliminarReserva)
        val btn_rollbackReserva = findViewById<ImageButton>(R.id.btn_rollbackReserva)

        //BOTON EDITAR RESERVA
        btn_editarReserva.setOnClickListener {
            val intent = Intent(this@Reserva,Servicio::class.java)
            startActivity(intent)
        }

        //BOTON CONFIRMAR ELIMINACION
        btn_eliminarReserva.setOnClickListener {
            val builder = AlertDialog.Builder(this@Reserva)
            builder.setTitle("Confirmar")
            builder.setMessage("Confirmar eliminación")
            builder.setPositiveButton(android.R.string.ok){
                dialog,
                    wich -> Toast.makeText(this,"Eliminación exitosa",Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancelar",null)
            builder.show()
        }

        //BOTON VOLVER
        btn_rollbackReserva.setOnClickListener {
            val intent = Intent(this@Reserva,Menu_Reservas::class.java)
            startActivity(intent)
        }
    }
}