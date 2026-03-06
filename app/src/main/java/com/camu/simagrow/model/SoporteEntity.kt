package com.camu.simagrow.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "soporte")
data class SoporteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val nia: String,
    val asunto: String,
    val mensaje: String,
    val fecha: String
)

