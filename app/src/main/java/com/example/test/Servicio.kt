package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Servicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)

        //MS = menu_Servicio

        val btn_rollback = findViewById<ImageButton>(R.id.btn_rollback)
        val btn_reservar = findViewById<Button>(R.id.btn_reservar)

        //Boton volver
        btn_rollback.setOnClickListener {
            val intent = Intent(this@Servicio,Menu_Locales::class.java)
            startActivity(intent)
        }

        //Boton reservar
        btn_reservar.setOnClickListener {
            val intent = Intent(this@Servicio,Menu_Locales::class.java)
            startActivity(intent)
        }
    }
}

