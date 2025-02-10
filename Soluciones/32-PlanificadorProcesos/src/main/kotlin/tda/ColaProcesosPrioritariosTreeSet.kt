package dev.joseluisgs

import dev.joseluisgs.models.Proceso
import java.util.*

class ColaProcesosPrioritariosTreeSet : Cola<Proceso> {
    private val cola = TreeSet<Proceso> { p1, p2 ->
        // Si la prioridad es distinta, ordenamos por prioridad
        if (p1.prioridad != p2.prioridad) {
            p1.prioridad.compareTo(p2.prioridad)
        } else {
            // Si la prioridad es igual, ordenamos por tiempo
            p1.id.compareTo(p2.id)
        }
    }

    override fun enqueue(elemento: Proceso) {
        cola.add(elemento)
    }

    override fun dequeue(): Proceso? {
        return cola.removeFirst()
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun first(): Proceso? {
        return cola.first()
    }

    override fun size(): Int {
        return cola.size
    }

    override fun toString(): String {
        return cola.joinToString(separator = ", ", prefix = "[", postfix = "]", limit = 10, truncated = "...")
    }
}