package com.jonkandidi.bibliotecadejuegos.entidades

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuarios(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @NonNull @ColumnInfo val nombreUsuario: String = "",
    @NonNull @ColumnInfo val contraseña: String = ""
){}