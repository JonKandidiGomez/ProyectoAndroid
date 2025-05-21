package com.jonkandidi.bibliotecadejuegos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Juego(
    @PrimaryKey(autoGenerate = true)
    val titulo: String,
    val año: Int,
    val desarrollador: String,
    val resumen: String,
    val imagen: ByteArray
){}