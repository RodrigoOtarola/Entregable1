package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.room.Room

class Front_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_principal)

        val btn_editPerfil = findViewById<ImageButton>(R.id.btn_editPerfilHistorial)
        val btn_MReservas = findViewById<Button>(R.id.btn_Mreservas)
        val tv_user = findViewById<TextView>(R.id.tv_user)


        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        //Tomar variable mail desde la actividad MainActivity, se le debe pasar el nombre de la llave
        val mail: String = intent.getStringExtra("mail").toString()
        tv_user.setText("${mail}")

        btn_editPerfil.setOnClickListener {
            val intent = Intent (this@Front_Principal,Editar_Perfil::class.java)
            startActivity(intent)
        }

        btn_MReservas.setOnClickListener {
            val intent = Intent (this@Front_Principal,Menu_Reservas::class.java)
            intent.putExtra("mail", mail)
            startActivity(intent)
        }

    }
}