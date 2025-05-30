package com.jonkandidi.bibliotecadejuegos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonkandidi.bibliotecadejuegos.adaptador.Adaptador
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentMisJuegosBinding

class MisJuegosFragment : Fragment() {

    private var _binding: FragmentMisJuegosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adaptador: Adaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMisJuegosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptador = Adaptador(
            mutableListOf(),
            onVerClick = { juego ->
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragment2ToVerJuegoFragment(juego)
                findNavController().navigate(action)
            },
            onEditarClick = { juego ->
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragment2ToInsertarEditarFragment(juego.id)
                findNavController().navigate(action)
            },
            onBorrarClick = { juego ->
                (activity as MainActivity).miViewModel.borrar(juego)
            }
        )

        binding.llMisJuegos.layoutManager = LinearLayoutManager(requireContext())
        binding.llMisJuegos.adapter = adaptador

        (activity as MainActivity).miViewModel.listaJuegos.observe(viewLifecycleOwner) { lista ->
            adaptador.setData(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
