package com.example.taler2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taler2.R

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvRegister: TextView = findViewById(R.id.tv_reg_pt2)
        val tvRecPassword: TextView = findViewById(R.id.tv_rec_password)
        val btLogin: TextView = findViewById(R.id.bt_login)
        val etEmail: EditText = findViewById(R.id.cont_email)
        val etPassword: EditText = findViewById(R.id.cont_email2)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        btLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validar credenciales
            if (validateCredentials(email, password)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finalizar LoginActivity para evitar volver con "Atrás"
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvRecPassword.setOnClickListener {
            val intent = Intent(this, RecPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        // Recuperar las credenciales guardadas en SharedPreferences
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // Comparar las credenciales ingresadas con las guardadas
        return email == savedEmail && password == savedPassword
    }
}