package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.test.entity.Usuario
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Inicializar DDBB
        val room =
            Room.databaseBuilder(this, DDBB::class.java, "estetica.db").allowMainThreadQueries()
                .build()

        val name = findViewById<TextInputLayout>(R.id.name)
        val first_name = findViewById<TextInputLayout>(R.id.first_name)
        val email = findViewById<TextInputLayout>(R.id.email)
        val password = findViewById<TextInputLayout>(R.id.password)
        val btn_saveUSer = findViewById<Button>(R.id.btn_updateUser)

        btn_saveUSer.setOnClickListener {
            var Name = name.editText?.text.toString()
            var First_name = first_name.editText?.text.toString()
            val Email = email.editText?.text.toString()
            val Password = password.editText?.text.toString()

            //Insertar info
            val usuario = Usuario(Email,Name, First_name , Password)

            if (validarCampos() == 0) {
                lifecycleScope.launch {
                    val id = room.daoUsuario().agregarUsuario(usuario)
                    if (id > 0) {
                        Log.d("IDuser", id.toString())
                        Toast.makeText(this@Register, "Registro exitoso", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@Register, MainActivity::class.java)
                        startActivity(intent)
                    }
                    //OPCIONAL
                    val respuesta = room.daoUsuario().obtenerUsuarios()
                    for (elemento in respuesta) {
                        println(elemento.toString())
                    }
                }
            }
        }
    }

    //Validar campos desde Validate.kt
    fun validarCampos(): Int {

        //Contador de errores
        var contador: Int = 0

        val name = findViewById<TextInputLayout>(R.id.name)
        val first_name = findViewById<TextInputLayout>(R.id.first_name)
        val email = findViewById<TextInputLayout>(R.id.email)
        val password = findViewById<TextInputLayout>(R.id.password)

        //para mostrar errores en layout
        var Name = name.editText?.text.toString()
        var First_name = first_name.editText?.text.toString()
        var Email = email.editText?.text.toString()
        var Password = password.editText?.text.toString()

        //Instancia de la clase
        val validate = Validation()

        //Validar campo usuario y formato correo
        if (validate.validarCampoNulo(Name)) {
            name.error = getString(R.string.error_name)
            contador++
        } else {
            name.error = ""
        }

        if (validate.validarCampoNulo(First_name)) {
            first_name.error = getString(R.string.error_name)
            contador++
        } else {
            first_name.error = ""
        }
        //Correo
        if (validate.validarCampoNulo(Email)) {
            email.error = getString(R.string.error_user)
            contador++
        } else {
            if (validate.validarFormatoCorreo(Email)) {
                email.error = getString(R.string.error_user_mail)
                contador++
            } else {
                email.error = ""
            }
        }

        //Validar contraseña
        if (validate.validarCampoNulo(Password)) {
            password.error = getString(R.string.error_pass)
            contador++
        } else {
            password.error = ""
        }

        //VALIDAR CONTRASEÑAS IGUALES.
        /*if(password != confirm_password){
            password.error = getString(R.string.equals_pass)
            confirm_password.error = getString(R.string.equals_pass)
            contador ++
        }else{
            password.error=""
            confirm_password.error=""
        }*/
        return contador
    }
}