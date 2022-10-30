package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Menu_Servicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_servicio)

        //REFERENCIAS
        val btn_rollbackMServ = findViewById<ImageButton>(R.id.btn_rollbackMServ)

        btn_rollbackMServ.setOnClickListener {
            val intent = Intent(this@Menu_Servicio,Menu_Locales::class.java)
            startActivity(intent)
        }

    }
}
