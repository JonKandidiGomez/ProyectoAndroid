package com.jonkandidi.bibliotecadejuegos.entidades

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Juego(
    @PrimaryKey(autoGenerate = true)
    @NonNull @ColumnInfo(name = "titulo")val titulo: String,
    @NonNull @ColumnInfo(name = "año")val año: Int,
    @NonNull @ColumnInfo(name = "desarrollador")val desarrollador: String,
    @ColumnInfo(name = "resumen")val resumen: String,
    @ColumnInfo(name = "imagen")val imagen: ByteArray
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Juego

        if (año != other.año) return false
        if (titulo != other.titulo) return false
        if (desarrollador != other.desarrollador) return false
        if (resumen != other.resumen) return false
        if (!imagen.contentEquals(other.imagen)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = año
        result = 31 * result + titulo.hashCode()
        result = 31 * result + desarrollador.hashCode()
        result = 31 * result + resumen.hashCode()
        result = 31 * result + imagen.contentHashCode()
        return result
    }
}