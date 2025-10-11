package com.example.taler2.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.taler2.R

class ProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tvName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var btEdit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inicializar vistas
        tvName = view.findViewById(R.id.tv_name)
        tvLastName = view.findViewById(R.id.tv_lastName)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_phone)
        btEdit = view.findViewById(R.id.bt_edit)

        // Obtener datos del SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)
        loadUserData()

        // Configurar botón de edición
        btEdit.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EditProfileFragment()) // Cargar el fragmento de edición
                .addToBackStack(null) // Agregar a la pila para regresar al perfil
                .commit()
        }

        return view
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
}