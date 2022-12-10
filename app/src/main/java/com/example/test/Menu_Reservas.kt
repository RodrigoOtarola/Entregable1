package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.room.Room

class Menu_Reservas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_reservas)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        //Tomar variable mail desde la actividad MainActivity, se le debe pasar el nombre de la llave
        val mail: String = intent.getStringExtra("mail").toString()

        //REFERENCIAS
        val btn_irRealizarReserva = findViewById<ImageButton>(R.id.btn_irRealizarReserva)
        val btn_irEditarReserva = findViewById<ImageButton>(R.id.btn_irEditarReserva)
        val btn_historialReserva= findViewById<ImageButton>(R.id.btn_historialReserva)
        val btn_editPerfilHistorial = findViewById<ImageButton>(R.id.btn_editPerfilHistorial)



        //BOTON REALIZAR RESERVA
        btn_irRealizarReserva.setOnClickListener {
            val intent = Intent(this@Menu_Reservas,Menu_Realizar_Reserva::class.java)
            intent.putExtra("mail", mail)
            startActivity(intent)
        }

        //BOTON EDITAR RESERVA
        btn_irEditarReserva.setOnClickListener {
            val intent = Intent(this@Menu_Reservas,Reserva::class.java)
            intent.putExtra("mail", mail)
            startActivity(intent)
        }

        //BOTON HISTORIAL
        btn_historialReserva.setOnClickListener {
            val intent = Intent(this@Menu_Reservas,Menu_Historial::class.java)
            intent.putExtra("mail", mail)
            startActivity(intent)
        }

        //BOTON EDITAR PERFIL
        btn_editPerfilHistorial.setOnClickListener {
            val intent = Intent(this@Menu_Reservas,Editar_Perfil::class.java)
            intent.putExtra("mail", mail)
            startActivity(intent)
        }
    }
}