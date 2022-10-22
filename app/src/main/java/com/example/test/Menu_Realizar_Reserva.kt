package com.example.test

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Menu_Realizar_Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_realizar_reserva)

        val date = findViewById<TextInputLayout>(R.id.date)
        val hour = findViewById<TextInputLayout>(R.id.hour)
        val btn_rollbackMRR = findViewById<ImageButton>(R.id.btn_rollbackMRR)
        val btn_saveReserve = findViewById<ImageButton>(R.id.btn_saveReserve)

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

        //Boton volver
        btn_rollbackMRR.setOnClickListener {
            val intent = Intent(this@Menu_Realizar_Reserva,Front_Principal::class.java)
            startActivity(intent)
        }

        //Boton de guardar con validacion
        btn_saveReserve.setOnClickListener {
            var Date = date.editText?.text.toString()
            var Hour = hour.editText?.text.toString()
            if (validarCampos()==0) {
                Toast.makeText(this, "Reserva realizada con exito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Menu_Realizar_Reserva, Menu_Realizar_Reserva::class.java)
                startActivity(intent)
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