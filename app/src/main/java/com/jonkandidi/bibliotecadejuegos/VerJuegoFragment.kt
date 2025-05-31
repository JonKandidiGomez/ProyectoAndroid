package com.jonkandidi.bibliotecadejuegos

import android.graphics.BitmapFactory
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

        val menuHost: MenuHost = requireActivity()
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED).not()) {
            menuHost.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_ver_juego, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_ver_juegos -> {
                            findNavController().navigate(R.id.misJuegosFragment)
                            true
                        }

                        R.id.action_insertar_juego -> {
                            findNavController().navigate(R.id.insertarEditarFragment)
                            true
                        }

                        R.id.action_logout -> {
                            (activity as MainActivity).miViewModel.usuario = null
                            findNavController().navigate(R.id.loginFragment)
                            true
                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

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