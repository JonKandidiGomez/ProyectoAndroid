package com.jonkandidi.bibliotecadejuegos.modelo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jonkandidi.bibliotecadejuegos.entidades.Juego
import kotlinx.coroutines.flow.Flow

@Dao
interface JuegoDAO {

    @Query("SELECT * FROM juegos ORDER BY TITULO ASC")
    fun mostrarJuegos(): Flow<List<Juego>>

    @Query("SELECT * FROM JUEGOS where ID like :id")
    fun buscarJuego(id:Int): Flow<Juego>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(juego: Juego)

    @Delete
    suspend fun borrar(juego: Juego)

    @Update
    suspend fun modificar(juego: Juego)

}