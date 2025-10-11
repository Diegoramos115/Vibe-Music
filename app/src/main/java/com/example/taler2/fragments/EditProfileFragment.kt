package com.example.taler2.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taler2.R

class EditProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var contName: EditText
    private lateinit var contLastName: EditText
    private lateinit var contEmail: EditText
    private lateinit var contPhone: EditText
    private lateinit var contPassword: EditText
    private lateinit var contRePassword: EditText
    private lateinit var btnSaveChanges: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        // Inicializar vistas
        contName = view.findViewById(R.id.cont_name)
        contLastName = view.findViewById(R.id.cont_lastName)
        contEmail = view.findViewById(R.id.cont_email)
        contPhone = view.findViewById(R.id.cont_phone)
        contPassword = view.findViewById(R.id.cont_password)
        contRePassword = view.findViewById(R.id.cont_rePassword)
        btnSaveChanges = view.findViewById(R.id.btn_save_changes)

        // Obtener SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserData", android.content.Context.MODE_PRIVATE)

        // Cargar datos existentes
        loadUserData()

        // Configurar botón de guardar cambios
        btnSaveChanges.setOnClickListener {
            saveUpdatedData()
        }

        return view
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
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != rePassword) {
            Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.putString("password", password)
        editor.apply()

        Toast.makeText(requireContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()

        // Regresar al fragmento anterior o al perfil
        requireActivity().supportFragmentManager.popBackStack()
    }
}