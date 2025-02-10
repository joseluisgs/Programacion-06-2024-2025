package dev.joseluisgs

import dev.joseluisgs.models.Proceso
import java.util.*

class ColaProcesosPrioritariosTreeMapMap : Cola<Proceso> {
    // podria ser un hashMap, o un MutableMap por defecto
    private val cola: TreeMap<Int, TreeMap<Int, Proceso>> = TreeMap()

    override fun enqueue(elemento: Proceso) {
        /*val prioridad = elemento.prioridad
        val procesos = cola.getOrPut(prioridad) { TreeMap() }
        val id = elemento.id
        procesos[id] = elemento*/

        // Otra forma de hacerlo
        cola.computeIfAbsent(elemento.prioridad) { TreeMap() }[elemento.id] = elemento

    }

    override fun dequeue(): Proceso {
        // forma más rapida de hacerlo
        /*val prioridad = cola.firstKey()
        val procesos = cola[prioridad]
        val proceso = procesos?.values?.first()
        if (procesos != null) {
            if (procesos.size > 1) {
                procesos.remove(proceso?.id)
            } else {
                cola.remove(prioridad)
            }
        }
        return proceso*/

        // otra forma con programación funcional
        return cola.values.first().values.first().also {
            cola.values.first().remove(it.id)
            if (cola.values.first().isEmpty()) {
                cola.remove(cola.firstKey())
            }
        }
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun first(): Proceso {
        return cola.values.first().values.first()
    }

    override fun size(): Int {
        return cola.values.sumOf { it.size }
    }

    override fun toString(): String {
        //return cola.values.joinToString(separator = ", ", prefix = "[", postfix = "]", limit = 10, truncated = "...")
        return cola.toString()
    }
}