package dev.joseluisgs.queue

import dev.joseluisgs.list.linkedListOf

/**
 * Implementación de una cola, usando una lista enlazada basado en FIFO (First In First Out)
 * Insertamos por el final de la lista y eliminamos por el principio
 */
class Cola<T> : Queue<T> {
    private val lista = linkedListOf<T>()

    /**
     * Añade un elemento a la cola, siempre al final
     * @param element Elemento a añadir
     */
    override fun enqueue(element: T) {
        lista.add(element)
    }

    /**
     * Elimina el primer elemento de la cola
     * @return Elemento eliminado
     * @throws NoSuchElementException si la cola está vacía
     */
    override fun dequeue(): T {
        if (isEmpty()) throw NoSuchElementException("Cola vacía")
        return lista.removeAt(0)
    }

    /**
     * Devuelve el primer elemento de la cola sin eliminarlo
     * @return Elemento en frente de la cola
     * @throws NoSuchElementException si la cola está vacía
     */
    override fun peek(): T {
        if (isEmpty()) throw NoSuchElementException("Cola vacía")
        return lista[0]
    }

    override val size: Int
        get() = lista.size

    override fun isEmpty(): Boolean = lista.isEmpty()

    override fun toString() = lista.toString()
}