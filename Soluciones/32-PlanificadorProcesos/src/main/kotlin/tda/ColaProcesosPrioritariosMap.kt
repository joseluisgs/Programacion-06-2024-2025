package dev.joseluisgs

import dev.joseluisgs.models.Proceso

class ColaProcesosPrioritariosMap : Cola<Proceso> {
    private val cola = mutableMapOf<Int, Proceso>()

    override fun enqueue(elemento: Proceso) {
        cola[elemento.id] = elemento
    }

    override fun dequeue(): Proceso? {
        return cola.values.minByOrNull { it.prioridad }?.also {
            cola.remove(it.id) // como estamos usando el id como clave, lo eliminamos
        }
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun first(): Proceso {
        return cola.values.first()
    }

    override fun size(): Int {
        return cola.size
    }

    override fun toString(): String {
        //return cola.values.joinToString(separator = ", ", prefix = "[", postfix = "]", limit = 10, truncated = "...")
        return cola.toString()
    }
}