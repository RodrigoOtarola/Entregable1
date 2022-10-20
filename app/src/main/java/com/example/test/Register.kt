package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<TextInputLayout>(R.id.name)
        val first_name = findViewById<TextInputLayout>(R.id.first_name)
        val email = findViewById<TextInputLayout>(R.id.email)
        val password = findViewById<TextInputLayout>(R.id.password)
        val confirm_password = findViewById<TextInputLayout>(R.id.confirm_password)
        val btn_saveUSer = findViewById<Button>(R.id.btn_updateUser)

        btn_saveUSer.setOnClickListener {

            var Name = name.editText?.text.toString()
            var First_name = first_name.editText?.text.toString()

            if (validarCampos() == 0) {
                val intent = Intent(this@Register, MainActivity::class.java)
                Toast.makeText(this, "Registro con exito", Toast.LENGTH_SHORT)
                    .show()
                startActivity(intent)
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
        val confirm_password = findViewById<TextInputLayout>(R.id.confirm_password)

        //para mostrar errores en layout
        var Name = name.editText?.text.toString()
        var First_name = first_name.editText?.text.toString()
        var Email = email.editText?.text.toString()
        var Password = password.editText?.text.toString()
        var Confirm_password = confirm_password.editText?.text.toString()

        //Instancia de la clase
        val validate = Validation()

        //Validar campo usuario y formato correo
        if (validate.validarCampoNulo(Name)) {
            name.error = getString(R.string.error_name)
            contador++
        }else {
            name.error = ""
        }

        if (validate.validarCampoNulo(First_name)) {
            first_name.error = getString(R.string.error_name)
            contador++
        }
        else {
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
        if (validate.validarCampoNulo(Confirm_password)){
            confirm_password.error = getString(R.string.error_name)
            contador++
        }else{
            confirm_password.error = ""
        }

        return contador
    }
}