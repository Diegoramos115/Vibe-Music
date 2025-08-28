package com.example.taler2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvRegister: TextView = findViewById(R.id.tv_reg_pt2)
        val tvRecPassword: TextView = findViewById(R.id.tv_rec_password)
        val btLogin: TextView = findViewById(R.id.bt_login)

        btLogin.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
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
}
