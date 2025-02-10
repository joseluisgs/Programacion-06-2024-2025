package dev.joseluisgs.models

import java.time.LocalDateTime

data class Proceso(
    val id: Int,
    private var _tiempo: Int = (1..20).random(),
    private var _prioridad: Int = (1..9).random(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    var tiempo: Int
        get() = _tiempo
        set(value) {
            _tiempo = if (value < 0) {
                0
            } else {
                value
            }
            updatedAt = LocalDateTime.now()
        }

    var prioridad: Int
        get() = _prioridad
        set(value) {
            _prioridad = if (value < 1) {
                1
            } else if (value > 9) {
                9
            } else {
                value
            }
        }

    val isActive: Boolean
        get() = _tiempo > 0

    override fun toString(): String {
        return "Proceso(id=$id, tiempo=$tiempo, prioridad=$prioridad, createdAt=$createdAt, updatedAt=$updatedAt)"
    }

}
