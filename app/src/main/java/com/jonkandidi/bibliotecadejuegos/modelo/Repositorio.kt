package com.jonkandidi.bibliotecadejuegos.modelo

import androidx.annotation.WorkerThread
import com.jonkandidi.bibliotecadejuegos.entidades.Juego
import kotlinx.coroutines.flow.Flow

class Repositorio (val miDAO: JuegoDAO) {
    fun mostrarJuegos(): Flow<List<Juego>> {
        return miDAO.mostrarJuegos()
    }
    @WorkerThread
    suspend fun insertar(juego: Juego){
        miDAO.insertar(juego)
    }
    @WorkerThread
    suspend fun borrar(juego: Juego){
        miDAO.borrar(juego)
    }

    @WorkerThread
    suspend fun modificar(juego: Juego){
        miDAO.modificar(juego)
    }
    fun buscarJuego(id:Int): Flow<Juego?>{
        return miDAO.buscarJuego(id)
    }
}