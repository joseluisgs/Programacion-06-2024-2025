package dev.joseluisgs

import dev.joseluisgs.models.Proceso

class ColaProcesosLista : Cola<Proceso> {
    private val cola: MutableList<Proceso> = mutableListOf()

    override fun enqueue(elemento: Proceso) {
        cola.add(elemento)
    }

    override fun dequeue(): Proceso? {
        return cola.removeFirst()
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun first(): Proceso {
        return cola.first()
    }

    override fun size(): Int {
        return cola.size
    }

    override fun toString(): String {
        // return cola.joinToString(separator = ", ", prefix = "[", postfix = "]", limit = 10, truncated = "...")
        return cola.toString()
    }
}