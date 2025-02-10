package dev.joseluisgs.models

import java.time.LocalDateTime

data class Estudiante(
    val id: Long = NEW_ID,
    val nombre: String,
    val edad: Int,
    val calificacion: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false,
) {
    companion object {
        val NEW_ID: Long = 0L
    }

    val isAprobado: Boolean
        get() = calificacion >= 5.0

}
