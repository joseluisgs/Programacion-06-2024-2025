package dev.joseluisgs.tda

import dev.joseluisgs.Cola
import dev.joseluisgs.models.Proceso
import java.util.*
import kotlin.collections.ArrayDeque

class ColaProcesosPrioritariosMapArrayDeque : Cola<Proceso> {
    private val cola: TreeMap<Int, ArrayDeque<Proceso>> = TreeMap()

    override fun enqueue(elemento: Proceso) {
        // Insertamos en la cola de prioridad
        if (cola.containsKey(elemento.prioridad)) {
            cola[elemento.prioridad]?.add(elemento)
        } else {
            cola[elemento.prioridad] = ArrayDeque()
            cola[elemento.prioridad]?.add(elemento)
        }
    }

    override fun dequeue(): Proceso? {
        // Sacamos de la cola de prioridad
        // Si la cola de prioridad está vacía, la eliminamos del mapa
        // Devolvemos el primer proceso de la cola de prioridad, siempre y cuando no esté vacía
        // En caso contrario, devolvemos null para indicar que la cola está vacía
        return cola.values.firstOrNull()?.let {
            val proceso = it.removeFirst()
            if (it.isEmpty()) {
                cola.remove(cola.firstKey())
            }
            proceso
        }
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun first(): Proceso? {
        return cola.values.firstOrNull()?.firstOrNull()
    }

    override fun size(): Int {
        return cola.values.sumOf { it.size }
    }

    override fun toString(): String {
        return cola.toString()
    }
}