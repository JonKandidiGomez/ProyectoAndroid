package com.jonkandidi.bibliotecadejuegos

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentLoginBinding
import com.jonkandidi.bibliotecadejuegos.modelo.Usuario


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
        val nombreUsuario = datos.getString("nombre", null)
        val contraseña = datos.getString("apellidos", null)

        if (nombreUsuario != null && contraseña != null) {
            (activity as MainActivity).miVM.usuario = Usuario(nombreUsuario, contraseña)
            findNavController().navigate(R.id.action_loginFragment_to_principalFragment)
        }

        binding.bLogin.setOnClickListener {

        }
    }
}