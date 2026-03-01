package com.camu.simagrow.pojo

data class Profesor(
    val nia: String,
    val password: String,
    val nombre: String = "",
    val materia: String = ""
)