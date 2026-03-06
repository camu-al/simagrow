package com.camu.simagrow.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.camu.simagrow.model.SoporteEntity

@Dao
interface SoporteDao {
    @Insert
    suspend fun insertarMensajeSoporte(soporte: SoporteEntity)

    @Query("SELECT * FROM soporte ORDER BY fecha DESC")
    suspend fun obtenerMensajesSoporte(): List<SoporteEntity>
}
