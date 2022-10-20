package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_register = findViewById<Button>(R.id.btn_register)

        //Boton iniciar sesión
        btn_login.setOnClickListener {
            val intent = Intent(this@MainActivity,Front_Principal::class.java)
            startActivity(intent)
        }

        //Boton de re registro
        btn_register.setOnClickListener {
            val intent = Intent(this@MainActivity,Register::class.java)
            startActivity(intent)
        }
    }


}