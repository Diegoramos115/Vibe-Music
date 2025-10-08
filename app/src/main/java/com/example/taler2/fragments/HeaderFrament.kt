package com.example.taler2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.taler2.R
import com.example.taler2.activities.ProfileActivity
import com.google.android.material.navigation.NavigationView

class HeaderFragment : Fragment() {

    private var drawerLayout: DrawerLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_header, container, false)

        val btnMenu = view.findViewById<ImageButton>(R.id.btn_menu)
        val navigationView = activity?.findViewById<NavigationView>(R.id.navigationView)

        // Configurar el DrawerLayout al cargar la vista
        btnMenu.post {
            drawerLayout = activity?.findViewById(R.id.drawerLayout)
        }

        // Manejar el clic del botón del menú lateral
        btnMenu.setOnClickListener {
            val drawer = drawerLayout
            if (drawer != null) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START)
                } else {
                    drawer.openDrawer(GravityCompat.START)
                }
            }
        }

        // Manejar clics en los elementos del menú
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            handleMenuItemClick(menuItem)
        }

        return view
    }

    private fun handleMenuItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.op2 -> {
                // Redirigir a ProfileActivity
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)
                drawerLayout?.closeDrawer(GravityCompat.START) // Cerrar el menú lateral
                return true
            }
            // Manejar otros ítems si es necesario
        }
        return false
    }
}