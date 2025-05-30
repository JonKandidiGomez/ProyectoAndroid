package com.jonkandidi.bibliotecadejuegos

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentLoginBinding
import com.jonkandidi.bibliotecadejuegos.modelo.Usuario
import androidx.core.content.edit


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datos:SharedPreferences= (activity as MainActivity).getSharedPreferences("datos",Context.MODE_PRIVATE)
        val nombreUsuario = datos.getString("nombreUsuario", null)
        val contraseña = datos.getString("contraseña", null)

        binding.bLogin.setOnClickListener {
            if (nombreUsuario != null && contraseña != null) {
                if (nombreUsuario == binding.etNombreUsuario.text.toString() && contraseña == binding.etContraseA.text.toString()) {
                    (activity as MainActivity).miViewModel.usuario = Usuario(nombreUsuario, contraseña)
                    findNavController().navigate(R.id.action_loginFragment_to_principalFragment)
                } else {
                    Toast.makeText((activity as MainActivity).baseContext, "ERROR. Nombre de usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (binding.etNombreUsuario.text.isNotEmpty() && binding.etContraseA.text.isNotEmpty()){
                    datos.edit {
                        putString("nombreUsuario", binding.etNombreUsuario.text.toString())
                        putString("contraseña", binding.etContraseA.text.toString())
                    }
                    (activity as MainActivity).miViewModel.usuario = Usuario(binding.etNombreUsuario.text.toString(),binding.etContraseA.text.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_principalFragment)
                } else {
                    Toast.makeText((activity as MainActivity).baseContext, "ERROR. Introduce un nombre de usuario y contraseña", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}