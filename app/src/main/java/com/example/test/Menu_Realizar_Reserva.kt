package com.example.test

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.test.entity.Reservas
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class Menu_Realizar_Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_realizar_reserva)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        //variable de usuario
        val mail: String = intent.getStringExtra("mail").toString()

        //REFERENCIAS
        val list_locales = findViewById<Spinner>(R.id.list_locales)
        val date = findViewById<TextInputLayout>(R.id.date)
        val hour = findViewById<TextInputLayout>(R.id.hour)
        val btn_rollbackMRR = findViewById<ImageButton>(R.id.btn_rollbackMRR)
        val btn_saveReserve = findViewById<ImageButton>(R.id.btn_saveReserve)
        val list_servicio = findViewById<Spinner>(R.id.list_servicio)

        //GENERACION DE SPINNER LOCALES
        val arrayAdapterLocales : ArrayAdapter<*>
        val Locales = arrayOf(
            "Selecciona el local",
            "Local 1",
            "Local 2",
            "Local 3",
            "Local 4"
        )
        arrayAdapterLocales = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Locales)
        list_locales.adapter = arrayAdapterLocales

        //Intancia de calendar
        val calendar = Calendar.getInstance();

        // Fecha
        val listenerDate = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            //Formato de fecha con 0
            var auxMonth = month + 1
            var setMonth = "$auxMonth"
            var setDay = "$day"
            if (auxMonth < 10) setMonth = "0$auxMonth"
            if (day < 10) setDay = "0$setDay"
            date.editText?.setText("$setDay/$setMonth/$year")
        }
        date.editText?.setOnClickListener {
            DatePickerDialog(
                this@Menu_Realizar_Reserva,
                listenerDate,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        //Hora
        val listenerHour = TimePickerDialog.OnTimeSetListener { timePicker, hours, minutes ->
            var auxHour = hours
            var setHour = "$auxHour"
            var setMinutes = "$minutes"
            if (auxHour < 10) setHour = "0$auxHour"
            if (minutes < 10) setMinutes = "0$setMinutes"
            hour.editText?.setText("$setHour:$setMinutes")
        }
        hour.editText?.setOnClickListener {
            TimePickerDialog(
                this@Menu_Realizar_Reserva,
                listenerHour,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        //GENERACION DE SPINNER SERVICIO
        val arrayAdapterServicio : ArrayAdapter<*>
        val Servicio = arrayOf(
            "Selecciona el servicio",
            "Peluqueria",
            "Manicure",
            "Pedicure",
            "Limpieza facial"
        )
        arrayAdapterServicio = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Servicio)
        list_servicio.adapter = arrayAdapterServicio

        //Boton volver
        btn_rollbackMRR.setOnClickListener {
            val intent = Intent(this@Menu_Realizar_Reserva,Menu_Reservas::class.java)
            startActivity(intent)
        }

        //Boton de guardar con validacion
        btn_saveReserve.setOnClickListener {
            //Para leer selección de spinner es con selectedItem
            var local  = list_locales.selectedItem.toString()
            var fecha = date.editText?.text.toString()
            var hora = hour.editText?.text.toString()
            var servicio  = list_servicio.selectedItem.toString()
            //Construir var id para el valor de la inserción si es mayor a 1 inserta.
            var id:Long = 0

            //Instancia del objeto Reservas
            var reservas = Reservas(local,fecha,hora, servicio,mail)

            if (validarCampos()==0) {
                //Corrutina
                lifecycleScope.launch{
                    id = room.daoReserva().agregarReserva(reservas)
                    //Ver informacion en el log
                    var respuesta = room.daoReserva().obtenerReserva()
                    for(elemento in respuesta){
                        println(elemento.toString())
                    }
                    if(id>0){
                        Toast.makeText(this@Menu_Realizar_Reserva, "Reserva realizada con exito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Menu_Realizar_Reserva, Menu_Reservas::class.java)
                        intent.putExtra("mail",mail)
                        startActivity(intent)
                    }

                }

            }
        }
    }

    //Validacion desde Validate.kt
    fun validarCampos(): Int {
        var contador: Int = 0
        val date = findViewById<TextInputLayout>(R.id.date)
        val hour = findViewById<TextInputLayout>(R.id.hour)

        //para mostrar errores en layout
        var Date = date.editText?.text.toString()
        var Hour = hour.editText?.text.toString()

        //Instancia de validacion
        val Validate = Validation()

        //Validar campos vacios
        if (Validate.validarCampoNulo(Date)) {
            date.error = getString(R.string.error_user)
            contador++
        } else {
            date.error = ""
        }
        if (Validate.validarCampoNulo(Hour)) {
            hour.error = getString(R.string.error_user)
            contador++
        } else {
            hour.error = ""
        }
        return contador

    }
}