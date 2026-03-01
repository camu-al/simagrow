package com.camu.simagrow.pojo

data class Alumno(
    val nia: String,
    val password: String,
    val nombre: String = "",
    val curso: String = ""
)