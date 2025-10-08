package com.example.taler2.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taler2.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var btEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Asegúrate de que este sea el nombre correcto del layout

        // Inicializar las vistas
        tvName = findViewById(R.id.tv_name)
        tvLastName = findViewById(R.id.tv_lastName)
        tvEmail = findViewById(R.id.tv_email)
        tvPhone = findViewById(R.id.tv_phone)
        btEdit = findViewById(R.id.bt_edit)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // Cargar los datos del usuario
        loadUserData()
    }

    private fun loadUserData() {
        // Recuperar los datos guardados en SharedPreferences
        val name = sharedPreferences.getString("name", "Nombre no disponible")
        val lastName = sharedPreferences.getString("lastName", "Apellido no disponible")
        val email = sharedPreferences.getString("email", "Correo no disponible")
        val phone = sharedPreferences.getString("phone", "Teléfono no disponible")

        // Asignar los datos a los TextView
        tvName.text = name
        tvLastName.text = lastName
        tvEmail.text = email
        tvPhone.text = phone
    }
}