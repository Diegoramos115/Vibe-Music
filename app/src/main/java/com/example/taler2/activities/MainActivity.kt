package com.example.taler2.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.taler2.R
import com.example.taler2.activities.ProfileActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        // Configurar los clics en el menú lateral
        setupNavigationMenu()
    }

    private fun setupNavigationMenu() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.op2 -> {
                    // Redirigir a ProfileActivity
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                // Aquí puedes manejar otros ítems del menú
                R.id.op1 -> {
                    // Por ejemplo, redirigir al Home o manejar otra opción
                }
                R.id.op3 -> {
                    // Manejar la opción de configuración o similar
                }
                R.id.op4 -> {
                    // Manejar el cierre de sesión o cualquier otra acción
                }
            }

            // Cerrar el menú lateral después de procesar el clic
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        // Cerrar el menú lateral si está abierto al presionar "Atrás"
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}