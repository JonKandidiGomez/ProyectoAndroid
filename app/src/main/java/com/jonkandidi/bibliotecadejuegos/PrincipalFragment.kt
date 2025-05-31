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
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentPrincipalBinding

class PrincipalFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED).not()) {
            menuHost.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_principal, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_logout -> {
                            (activity as MainActivity).miViewModel.usuario = null
                            findNavController().navigate(R.id.loginFragment)
                            true
                        }

                        R.id.action_ver_juegos -> {
                            findNavController().navigate(R.id.misJuegosFragment)
                            true
                        }

                        R.id.action_insertar_juego -> {
                            findNavController().navigate(R.id.insertarEditarFragment)
                            true
                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

        binding.bMisJuegos.setOnClickListener {
            findNavController().navigate(R.id.action_principalFragment_to_misJuegosFragment)
        }

        binding.bNuevoJuego.setOnClickListener {
            findNavController().navigate(R.id.action_principalFragment_to_insertarEditarFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


