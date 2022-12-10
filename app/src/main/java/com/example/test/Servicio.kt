package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class Servicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        //Tomar variable mail desde la actividad MainActivity, se le debe pasar el nombre de la llave
        val mail: String = intent.getStringExtra("mail").toString()
        //Recibir servicio
        val servicio: String = intent.getStringExtra("servicio").toString()


        //MS = menu_Servicio
        val til_local = findViewById<TextInputLayout>(R.id.til_local)
        val til_fecha = findViewById<TextInputLayout>(R.id.til_fecha)
        val til_hora = findViewById<TextInputLayout>(R.id.til_hora)
        val til_servicio = findViewById<TextInputLayout>(R.id.til_servicio)
        val tv_id = findViewById<TextView>(R.id.tv_id)

        val btn_rollbackServicio = findViewById<ImageButton>(R.id.btn_rollbackServicio)
        val btn_editarReservaS = findViewById<Button>(R.id.btn_editar)
        val btn_eliminar = findViewById<Button>(R.id.btn_eliminar)

        //corrutina
        lifecycleScope.launch {
            val respuesta = room.daoReserva().obtenerReservasPorServicio(servicio, mail)
            if (respuesta.size == 1) {
                for (element in respuesta) {
                    til_local.editText?.setText(element.local.toString())
                    til_fecha.editText?.setText(element.fecha.toString())
                    til_hora.editText?.setText(element.hora.toString())
                    til_servicio.editText?.setText(element.servicio.toString())
                    tv_id.setText(element.id.toString())
                }
            }
        }
        //Boton volver
        btn_rollbackServicio.setOnClickListener {
            val intent = Intent(this@Servicio, Reserva::class.java)
            startActivity(intent)
        }

        //Boton editar reservar
        btn_editarReservaS.setOnClickListener {
            lifecycleScope.launch {
                var local = til_local.editText?.text.toString()
                var fecha = til_fecha.editText?.text.toString()
                var hora = til_hora.editText?.text.toString()
                var servicioNuevo = til_servicio.editText?.text.toString()
                var id = tv_id.text.toString()
                val respuesta = room.daoReserva()
                    .actualizarReserva(local, fecha, hora, servicioNuevo, id.toLong())

                println(respuesta)
                val intent = Intent(this@Servicio, Reserva::class.java)
                Toast.makeText(this@Servicio, "Reserva editada con exito", Toast.LENGTH_SHORT).show()
                intent.putExtra("mail",mail)
                startActivity(intent)
            }
        }

        //Boton Eliminar
        btn_eliminar.setOnClickListener {
            val builder = AlertDialog.Builder(this@Servicio)
            builder.setTitle("Confirmar")
            builder.setMessage("Confirmar eliminación")
            builder.setPositiveButton(android.R.string.ok){
                    dialog,

                    wich ->
                lifecycleScope.launch{
                    var id = tv_id.text.toString()
                    val respuesta = room.daoReserva().borrarReserva(id.toLong())
                    println(respuesta)
                    val intent = Intent(this@Servicio,Reserva::class.java)
                    intent.putExtra("mail",mail)
                    startActivity(intent)
                }
            }
            builder.setNegativeButton("Cancelar",null)
            builder.show()
        }

    }
}

