package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        val user = findViewById<TextInputLayout>(R.id.user)
        val pass = findViewById<TextInputLayout>(R.id.pass)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_register = findViewById<Button>(R.id.btn_register)

        //Boton iniciar sesión
        btn_login.setOnClickListener {
            var User = user.editText?.text.toString()
            var Pass = pass.editText?.text.toString()

            if (validarCampos() == 0) {
                lifecycleScope.launch {
                    val response = room.daoUsuario().login(User, Pass)
                    if (response.size == 1) {
                        val intent = Intent(this@MainActivity, Front_Principal::class.java)
                        intent.putExtra("mail", User)
                        Toast.makeText(this@MainActivity, "Inicio exitoso", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Login fallido", Toast.LENGTH_SHORT)
                            .show()
                    }
                    //Mostrar en consola
                    val respuesta = room.daoUsuario().obtenerUsuarios()
                    for (elemento in respuesta) {
                        println(elemento.toString())
                    }
                }
            }
        }
        //Boton de registro
        btn_register.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
    }

    //Validar campos desde Validate.kt
    fun validarCampos(): Int {

        //Contador de errores
        var contador: Int = 0

        val user = findViewById<TextInputLayout>(R.id.user)
        val pass = findViewById<TextInputLayout>(R.id.pass)

        //para mostrar errores en layout
        var User = user.editText?.text.toString()
        var Pass = pass.editText?.text.toString()

        //Instancia de la clase
        val validate = Validation()

        //Validar campo usuario y formato correo
        if (validate.validarCampoNulo(User)) {
            user.error = getString(R.string.error_user)
            contador++
        } else {
            if (validate.validarFormatoCorreo(User)) {
                user.error = getString(R.string.error_user_mail)
                contador++
            } else {
                user.error = ""
            }
        }

        //Validar contraseña
        if (validate.validarCampoNulo(Pass)) {
            pass.error = getString(R.string.error_pass)
            contador++
        } else {
            pass.error = ""
        }
        return contador
    }
}