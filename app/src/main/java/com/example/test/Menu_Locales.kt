package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Menu_Locales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_locales)

        //REFERENCIA
        val btn_irMLL = findViewById<ImageButton>(R.id.btn_irMLL)
        val btn_irUbicacion = findViewById<ImageButton>(R.id.btn_irUbicacion)
        val btn_irServicios = findViewById<ImageButton>(R.id.btn_irServicios)

        //BOTON IR A MENU LISTA LOCALES
        btn_irMLL.setOnClickListener{
            val intent = Intent(this@Menu_Locales,Menu_Lista_Locales::class.java)
            startActivity(intent)
        }

        //BOTON IR A UBICACION
        btn_irUbicacion.setOnClickListener{
            val intent = Intent(this@Menu_Locales,MenuGPS::class.java)
            startActivity(intent)
        }

        //BOTON IR A SERVICIOS
        btn_irServicios.setOnClickListener{
            val intent = Intent(this@Menu_Locales,Menu_Servicio::class.java)
            startActivity(intent)
        }

    }
}