package com.jonkandidi.bibliotecadejuegos.adaptador

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jonkandidi.bibliotecadejuegos.MisJuegosFragmentDirections
import com.jonkandidi.bibliotecadejuegos.R
import com.jonkandidi.bibliotecadejuegos.databinding.RecyclerviewItemBinding
import com.jonkandidi.bibliotecadejuegos.entidades.Juego

class Adaptador(
    private val lista: MutableList<Juego>,
    private val onBorrarClick: (Juego) -> Unit
) : RecyclerView.Adapter<Adaptador.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val juego = lista[position]

        with(holder.binding) {
            rvTvTitulo.text = juego.titulo
            rvTvDesarrollador.text = juego.desarrollador

            if (juego.imagen?.isNotEmpty() == true) {
                rvIFoto.setImageBitmap(BitmapFactory.decodeByteArray(juego.imagen, 0, juego.imagen.size))
            } else {
                rvIFoto.setImageResource(R.mipmap.ic_launcher)
            }

            rvBVer.setOnClickListener {
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragment2ToVerJuegoFragment(juego)
                it.findNavController().navigate(action)
            }

            rvbEditar.setOnClickListener {
                val action = MisJuegosFragmentDirections
                    .actionMisJuegosFragment2ToInsertarEditarFragment(juegoId = juego.id)
                it.findNavController().navigate(action)
            }
            rvbBorrar.setOnClickListener { onBorrarClick(juego) }
        }
    }

    override fun getItemCount(): Int = lista.size

    fun setData(nuevaLista: List<Juego>) {
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}

