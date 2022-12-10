package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        //Tomar variable mail desde la actividad MainActivity, se le debe pasar el nombre de la llave
        val mail: String = intent.getStringExtra("mail").toString()

        //REFERENCIAS
        val btn_rollbackReserva = findViewById<ImageButton>(R.id.btn_rollbackReserva)
        val lv_reservas = findViewById<ListView>(R.id.lv_reservas)

        //Poblar ListView
        var arrayAdapterListView: ArrayAdapter<*>
        var reservas = ArrayList<String>()
        //Corrutina
        lifecycleScope.launch{
            var respuesta = room.daoReserva().obtenerReservasUsuario(mail)
            for(indice in respuesta.indices){
                //Muestra lista dinamica mostrando el servicio reservado
                reservas.add(respuesta[indice].servicio.toString())
            }
            arrayAdapterListView = ArrayAdapter(this@Reserva,android.R.layout.simple_list_item_1,reservas)

            lv_reservas.adapter = arrayAdapterListView
            lv_reservas.onItemClickListener = object :AdapterView.OnItemClickListener{
                override fun onItemClick(p0: AdapterView<*>?, view: View?, posicion: Int, id: Long) {
                    val intent = Intent(this@Reserva,Servicio::class.java)
                    //Pasas usuario a layout
                    intent.putExtra("mail",mail)

                    //Pasar reserva al layout
                    intent.putExtra("servicio","${reservas[posicion]}")

                    startActivity(intent)
                }
            }

            //BOTON CONFIRMAR ELIMINACION
            /*btn_eliminarReserva.setOnClickListener {
                val builder = AlertDialog.Builder(this@Reserva)
                builder.setTitle("Confirmar")
                builder.setMessage("Confirmar eliminación")
                builder.setPositiveButton(android.R.string.ok){
                    dialog,
                        wich -> Toast.makeText(this,"Eliminación exitosa",Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancelar",null)
                builder.show()
            }*/
        }

        //Ingreso a editar reserva

        //BOTON VOLVER
        btn_rollbackReserva.setOnClickListener {
            val intent = Intent(this@Reserva, Menu_Reservas::class.java)
            startActivity(intent)
        }
    }
}