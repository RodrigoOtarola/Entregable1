package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Menu_Lista_Locales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lista_locales)

        //MLL= menu_lista_locales

        val btn_rollbackMLL = findViewById<ImageButton>(R.id.btn_rollbackMLL)
        val btn_reserveMLL = findViewById<Button>(R.id.btn_reserveMLL)

        //Boton volver
        btn_rollbackMLL.setOnClickListener {
            val intent = Intent(this@Menu_Lista_Locales,Front_Principal::class.java)
            startActivity(intent)
        }

        //Boton reservar
        btn_reserveMLL.setOnClickListener {
            val intent = Intent(this@Menu_Lista_Locales,Menu_Realizar_Reserva::class.java)
            startActivity(intent)
        }
    }
}