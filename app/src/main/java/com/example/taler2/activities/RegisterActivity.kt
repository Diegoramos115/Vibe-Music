package com.example.taler2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taler2.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var contName: EditText
    private lateinit var contLastName: EditText
    private lateinit var contEmail: EditText
    private lateinit var contPhone: EditText
    private lateinit var contPassword: EditText
    private lateinit var contPassword2: EditText
    private lateinit var checkTerms: CheckBox
    private lateinit var btnGoLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        contName = findViewById(R.id.cont_name)
        contLastName = findViewById(R.id.cont_lastName)
        contEmail = findViewById(R.id.cont_email)
        contPhone = findViewById(R.id.cont_phone)
        contPassword = findViewById(R.id.cont_password)
        contPassword2 = findViewById(R.id.cont_rePassword)
        checkTerms = findViewById(R.id.check_terms)
        btnGoLogin = findViewById(R.id.btn_goLogin)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        btnGoLogin.setOnClickListener {
            val names = contName.text.toString().trim()
            val lastnames = contLastName.text.toString().trim()
            val email = contEmail.text.toString().trim()
            val phone = contPhone.text.toString().trim()
            val password = contPassword.text.toString().trim()
            val password2 = contPassword2.text.toString().trim()

            if (validateFields(names, lastnames, email, phone, password, password2)) {
                saveUserData(names, lastnames, email, phone, password)

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateFields(names: String, lastnames: String, email: String, phone: String, password: String, password2: String): Boolean {
        if (names.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if (lastnames.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor ingrese un email válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su teléfono", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password2.isEmpty()) {
            Toast.makeText(this, "Por favor confirme su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != password2) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!checkTerms.isChecked) {
            Toast.makeText(this, "Por favor acepte los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveUserData(name: String, lastName: String, email: String, phone: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.putString("password", password)
        editor.apply()
    }
}