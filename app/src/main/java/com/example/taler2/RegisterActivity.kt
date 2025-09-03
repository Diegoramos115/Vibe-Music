package com.example.taler2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // 👈 asegúrate que sea el XML correcto

        val btnGoLogin: Button = findViewById(R.id.btn_goLogin)
        val checkTerms: CheckBox = findViewById(R.id.check_terms)

        btnGoLogin.setOnClickListener {
            if (checkTerms.isChecked) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.txt_reg_exepTyC, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
