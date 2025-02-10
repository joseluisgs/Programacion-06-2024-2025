package dev.joseluisgs.factories

import dev.joseluisgs.models.Proceso

object ProcesosFactory {
    fun create(): Proceso {
        return Proceso(
            id = nextId,
            _tiempo = (1..20).random(),
            _prioridad = (1..9).random()
        )
    }

    private var lastId = 1
    private val nextId: Int
        get() = lastId++
}