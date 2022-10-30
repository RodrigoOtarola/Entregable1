package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Menu_Historial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_historial)

        //MH= Menu historial

        // declaro variables
        /*val btn_servicio1 = findViewById<ImageButton>(R.id.btn_servicio1)
        val btn_servicio2 = findViewById<ImageButton>(R.id.btn_servicio2)
        val btn_servicio3= findViewById<ImageButton>(R.id.btn_servicio3)*/
        val btn_rollbackMH = findViewById<ImageButton>(R.id.btn_rollbackMH)

        //BOTON SERVICIO 1 POR DEFINIR
        /*btn_servicio1.setOnClickListener {
            val intent = Intent(this@Menu_Historial,Menu_Realizar_Reserva::class.java)
            startActivity(intent)
        }

        //BOTON SERVICIO 2 POR DEFINIR
        btn_servicio2.setOnClickListener {
            val intent = Intent(this@Menu_Historial,Servicio::class.java)
            startActivity(intent)
        }

        //BOTON SERVICIO 3 POR DEFINIR
        btn_servicio3.setOnClickListener {
            val intent = Intent(this@Menu_Historial,Menu_Historial::class.java)
            startActivity(intent)
        }
*/
        //BOTON VOLVER A MENU RESERVA
        btn_rollbackMH.setOnClickListener {
            val intent = Intent(this@Menu_Historial,Menu_Reservas::class.java)
            startActivity(intent)
        }
    }
}

