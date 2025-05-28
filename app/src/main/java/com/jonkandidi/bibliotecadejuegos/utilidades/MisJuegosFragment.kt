package com.jonkandidi.bibliotecadejuegos.utilidades

import Adaptador
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonkandidi.bibliotecadejuegos.MainActivity
import com.jonkandidi.bibliotecadejuegos.R
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentMisJuegosBinding
import com.jonkandidi.bibliotecadejuegos.entidades.Juego


class MisJuegosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMisJuegosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_juegos, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.llMisJuegos.layoutManager=LinearLayoutManager(activity as MainActivity)
        binding.llMisJuegos.adapter = Adaptador((activity as MainActivity).miViewModel.listaJuegos as MutableList<Juego>)
    }
}