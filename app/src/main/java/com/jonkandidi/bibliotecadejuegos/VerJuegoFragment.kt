package com.jonkandidi.bibliotecadejuegos

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentVerJuegoBinding

class VerJuegoFragment : Fragment() {

    private var _binding: FragmentVerJuegoBinding? = null
    private val binding get() = _binding!!
    private val args: VerJuegoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerJuegoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val juego = args.juego

        binding.tvTitulo.text = juego.titulo
        binding.tvDesarrollador.text = juego.desarrollador
        binding.tvAnio.text = juego.año.toString()
        binding.tvResumen.text = juego.resumen

        if (juego.imagen?.isNotEmpty() == true) {
            val bitmap = BitmapFactory.decodeByteArray(juego.imagen, 0, juego.imagen.size)
            binding.ivImagenJuego.setImageBitmap(bitmap)
        } else {
            binding.ivImagenJuego.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}