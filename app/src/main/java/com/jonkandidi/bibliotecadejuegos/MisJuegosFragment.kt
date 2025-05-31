package com.jonkandidi.bibliotecadejuegos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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

        val menuHost: MenuHost = requireActivity()
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED).not()) {
            menuHost.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_misjuegos, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_logout -> {
                            (activity as MainActivity).miViewModel.usuario = null
                            findNavController().navigate(R.id.loginFragment)
                            true
                        }

                        R.id.action_insertar_juego -> {
                            findNavController().navigate(R.id.insertarEditarFragment)
                            true
                        }

                        R.id.action_volver_principal -> {
                            findNavController().navigate(R.id.principalFragment)
                            true
                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

        adaptador = Adaptador(
            mutableListOf(),
            onVerClick = { juego ->
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragmentToVerJuegoFragment(juego)
                findNavController().navigate(action)
            },
            onEditarClick = { juego ->
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragmentToInsertarEditarFragment(juego.id)
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

        binding.bAdd.setOnClickListener {
            findNavController().navigate(R.id.action_misJuegosFragment_to_insertarEditarFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
