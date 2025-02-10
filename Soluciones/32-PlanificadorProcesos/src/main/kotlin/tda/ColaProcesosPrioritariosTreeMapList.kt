package dev.joseluisgs

import dev.joseluisgs.models.Proceso
import java.util.*

class ColaProcesosPrioritariosTreeMapList : Cola<Proceso> {
    private val cola: TreeMap<Int, MutableList<Proceso>> = TreeMap()

    override fun enqueue(elemento: Proceso) {
        /*if (cola.containsKey(elemento.prioridad)) {
            cola[elemento.prioridad]?.add(elemento)
        } else {
            cola[elemento.prioridad] = mutableListOf(elemento)
        }*/
        // Lo importante de saberse el API de las colecciones :)
        // cola.computeIfAbsent(elemento.prioridad) { mutableListOf() }.add(elemento)

        // Otra forma de hacerlo
        cola.containsKey(elemento.prioridad).let {
            cola[elemento.prioridad]?.add(elemento)
        } ?: run {
            cola[elemento.prioridad] = mutableListOf(elemento)
        }

    }

    override fun dequeue(): Proceso? {
        /* val first = cola.values.firstOrNull()
         val proceso = first?.removeAt(0)
         if (first?.isEmpty() == true) {
             cola.remove(cola.firstKey())
         }
         return proceso*/

        // Otra forma de hacerlo
        return cola.values.firstOrNull()?.let {
            val proceso = it.removeAt(0)
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
        //return cola.values.joinToString(separator = ", ", prefix = "[", postfix = "]", limit = 10, truncated = "...")
        return cola.toString()
    }
}