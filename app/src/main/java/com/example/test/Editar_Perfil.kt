package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class Editar_Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        val btn_updateUser = findViewById<Button>(R.id.btn_updateUser)

        btn_updateUser.setOnClickListener {

            val intent = Intent (this@Editar_Perfil,Front_Principal::class.java)
            Toast.makeText(this, "Registro actualizado con exito", Toast.LENGTH_SHORT)
                .show()
            startActivity(intent)

        }
    }
}