package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Menu_Mapa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mapa)


        //declaro variable
        val btn_MenuMapaML = findViewById<ImageButton>(R.id.btn_MenuMapa)

        //Boton volver
        btn_MenuMapaML.setOnClickListener {
            val intent = Intent(this@Menu_Mapa, Menu_Locales::class.java)
            startActivity(intent)
        }

    }
}