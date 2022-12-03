package com.example.test

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class Editar_Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        val nameEdit = findViewById<TextInputLayout>(R.id.nameEdit)
        val first_nameEdit = findViewById<TextInputLayout>(R.id.first_nameEdit)
        val emailEdit = findViewById<TextInputLayout>(R.id.emailEdit)
        val passwordEdit = findViewById<TextInputLayout>(R.id.passwordEdit)
        val confirm_passwordEdit = findViewById<TextInputLayout>(R.id.confirm_passwordEdit)

        val btn_updateUser = findViewById<Button>(R.id.btn_updateUser)

        val btn_image = findViewById<Button>(R.id.btn_image)
        btn_image.setOnClickListener {
            val intentPhoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            asignarFoto.launch(intentPhoto)
        }


            btn_updateUser.setOnClickListener {

                var NameEdit = nameEdit.editText?.text.toString()
                var First_nameEdit = first_nameEdit.editText?.text.toString()
                var EmailEdit = emailEdit.editText?.text.toString()
                var PasswordEdit = passwordEdit.editText?.text.toString()
                var Confirm_passwordEdit = confirm_passwordEdit.editText?.text.toString()
                if (validarCampos() == 0) {
                    val intent = Intent(this@Editar_Perfil, Front_Principal::class.java)
                    Toast.makeText(this, "Registro actualizado con exito", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(intent)
                }
            }
        }

        //Validar campos desde Validate.kt
        fun validarCampos(): Int {

            //Contador de errores
            var contador: Int = 0

            val nameEdit = findViewById<TextInputLayout>(R.id.nameEdit)
            val first_nameEdit = findViewById<TextInputLayout>(R.id.first_nameEdit)
            val emailEdit = findViewById<TextInputLayout>(R.id.emailEdit)
            val passwordEdit = findViewById<TextInputLayout>(R.id.passwordEdit)
            val confirm_passwordEdit = findViewById<TextInputLayout>(R.id.confirm_passwordEdit)

            //para mostrar errores en layout
            var NameEdit = nameEdit.editText?.text.toString()
            var First_nameEdit = first_nameEdit.editText?.text.toString()
            var EmailEdit = emailEdit.editText?.text.toString()
            var PasswordEdit = passwordEdit.editText?.text.toString()
            var Confirm_passwordEdit = confirm_passwordEdit.editText?.text.toString()

            //Instancia de la clase
            val validate = Validation()

            //Validar campo usuario y formato correo
            if (validate.validarCampoNulo(NameEdit)) {
                nameEdit.error = getString(R.string.error_name)
                contador++
            } else {
                nameEdit.error = ""
            }

            if (validate.validarCampoNulo(First_nameEdit)) {
                first_nameEdit.error = getString(R.string.error_name)
                contador++
            } else {
                first_nameEdit.error = ""
            }
            //Correo
            if (validate.validarCampoNulo(EmailEdit)) {
                emailEdit.error = getString(R.string.error_user)
                contador++
            } else {
                if (validate.validarFormatoCorreo(EmailEdit)) {
                    emailEdit.error = getString(R.string.error_user_mail)
                    contador++
                } else {
                    emailEdit.error = ""
                }
            }

            //Validar contraseña
            if (validate.validarCampoNulo(PasswordEdit)) {
                passwordEdit.error = getString(R.string.error_pass)
                contador++
            } else {
                passwordEdit.error = ""
            }
            if (validate.validarCampoNulo(Confirm_passwordEdit)) {
                confirm_passwordEdit.error = getString(R.string.error_name)
                contador++
            } else {
                confirm_passwordEdit.error = ""
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

    private val asignarFoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.imageView2)
            imageView.setImageBitmap(imageBitmap)
        }
    }

    }
