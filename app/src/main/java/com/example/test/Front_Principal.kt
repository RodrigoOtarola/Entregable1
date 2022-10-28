package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Front_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_principal)

        val btn_editPerfil = findViewById<ImageButton>(R.id.btn_editPerfilHistorial)

        btn_editPerfil.setOnClickListener {
            val intent = Intent (this@Front_Principal,Editar_Perfil::class.java)
            startActivity(intent)
        }

    }
}