package com.jonkandidi.bibliotecadejuegos.entidades


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Juego(

    @PrimaryKey(autoGenerate = true)var id: Int = 0,
    @ColumnInfo(name = "titulo")val titulo: String,
    @ColumnInfo(name = "año")val año: Int,
    @ColumnInfo(name = "desarrollador")val desarrollador: String,
    @ColumnInfo(name = "resumen")val resumen: String?,
    @ColumnInfo(name = "imagen")val imagen: ByteArray?

): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Juego

        if (id != other.id) return false
        if (año != other.año) return false
        if (titulo != other.titulo) return false
        if (desarrollador != other.desarrollador) return false
        if (resumen != other.resumen) return false
        if (!imagen.contentEquals(other.imagen)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + año
        result = 31 * result + titulo.hashCode()
        result = 31 * result + desarrollador.hashCode()
        result = 31 * result + (resumen?.hashCode() ?: 0)
        result = 31 * result + (imagen?.contentHashCode() ?: 0)
        return result
    }
}