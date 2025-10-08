package com.example.taler2.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taler2.R

class EditProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var contName: EditText
    private lateinit var contLastName: EditText
    private lateinit var contEmail: EditText
    private lateinit var contPhone: EditText
    private lateinit var contPassword: EditText
    private lateinit var contRePassword: EditText
    private lateinit var btnSaveChanges: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        contName = findViewById(R.id.cont_name)
        contLastName = findViewById(R.id.cont_lastName)
        contEmail = findViewById(R.id.cont_email)
        contPhone = findViewById(R.id.cont_phone)
        contPassword = findViewById(R.id.cont_password)
        contRePassword = findViewById(R.id.cont_rePassword)
        btnSaveChanges = findViewById(R.id.btn_save_changes)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        loadUserData()

        btnSaveChanges.setOnClickListener {
            saveUpdatedData()
        }
    }

    private fun loadUserData() {
        val name = sharedPreferences.getString("name", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")
        val password = sharedPreferences.getString("password", "")

        contName.setText(name)
        contLastName.setText(lastName)
        contEmail.setText(email)
        contPhone.setText(phone)
        contPassword.setText(password)
        contRePassword.setText(password) // Mostrar la misma contraseña para confirmarla
    }

    private fun saveUpdatedData() {
        val name = contName.text.toString().trim()
        val lastName = contLastName.text.toString().trim()
        val email = contEmail.text.toString().trim()
        val phone = contPhone.text.toString().trim()
        val password = contPassword.text.toString().trim()
        val rePassword = contRePassword.text.toString().trim()

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != rePassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.putString("password", password)
        editor.apply()

        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()

        finish()
    }
}