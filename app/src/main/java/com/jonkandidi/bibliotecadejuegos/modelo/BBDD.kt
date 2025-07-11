package com.jonkandidi.bibliotecadejuegos.modelo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonkandidi.bibliotecadejuegos.entidades.Juego


@Database(entities = [Juego::class], version = 1, exportSchema = false)
abstract class BBDD : RoomDatabase() {
    abstract fun miDAO(): JuegoDAO

    companion object {
        @Volatile
        private var INSTANCE: BBDD? = null
        fun getDatabase(context: Context): BBDD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BBDD::class.java,
                    "biblioteca_de_juegos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}