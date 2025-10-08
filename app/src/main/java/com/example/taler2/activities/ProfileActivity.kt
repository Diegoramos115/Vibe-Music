package com.example.taler2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.taler2.R
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var btEdit: Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvName = findViewById(R.id.tv_name)
        tvLastName = findViewById(R.id.tv_lastName)
        tvEmail = findViewById(R.id.tv_email)
        tvPhone = findViewById(R.id.tv_phone)
        btEdit = findViewById(R.id.bt_edit)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        loadUserData()

        setupNavigationMenu()

        btEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadUserData() {
        val name = sharedPreferences.getString("name", "Nombre no disponible")
        val lastName = sharedPreferences.getString("lastName", "Apellido no disponible")
        val email = sharedPreferences.getString("email", "Correo no disponible")
        val phone = sharedPreferences.getString("phone", "Teléfono no disponible")

        tvName.text = name
        tvLastName.text = lastName
        tvEmail.text = email
        tvPhone.text = phone
    }

    private fun setupNavigationMenu() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.op2 -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)                }
                R.id.op1 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.op3 -> {
                }
                R.id.op4 -> {
                }
            }

            drawerLayout.closeDrawer(Gravity.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
        }
    }
}