package com.jonkandidi.bibliotecadejuegos.modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.jonkandidi.bibliotecadejuegos.entidades.Juego
import kotlinx.coroutines.launch

class JuegoViewModel(private val miRepositorio: Repositorio): ViewModel() {

    var usuario: Usuario? = null

    val listaJuegos: MutableLiveData<List<Juego>> = miRepositorio.mostrarJuegos().asLiveData() as MutableLiveData<List<Juego>>

    fun agregarJuego(juego: Juego) {
        val listaActual = listaJuegos.value ?: listOf()
        listaJuegos.value = listaActual.toMutableList().apply {
            add(juego)
        }
    }

    fun buscarJuegoPorId(id: Int) : LiveData<Juego?> {
        return miRepositorio.buscarJuego(id).asLiveData()
    }

    fun insertar(juego: Juego) = viewModelScope.launch {
        miRepositorio.insertar(juego)
    }

    fun borrar(juego: Juego) = viewModelScope.launch {
        miRepositorio.borrar(juego)
    }

    fun modificar(juego: Juego) = viewModelScope.launch {
        miRepositorio.modificar(juego)
    }
}

class JuegoViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JuegoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JuegoViewModel(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}