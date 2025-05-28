package com.jonkandidi.bibliotecadejuegos.entidades


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "Publicaciones")
data class Publicacion(
    @PrimaryKey(autoGenerate = true)
    val fecha: Date,
    val titulo: String
){}