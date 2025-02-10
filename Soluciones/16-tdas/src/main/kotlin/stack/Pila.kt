package dev.joseluisgs.stack

import dev.joseluisgs.list.linkedListOf

/**
 * Implementación de una pila, usando una lista enlazada basado en LIFO (Last In First Out)
 * Insertamos y eliminamos elementos por el final de la lista (o siempre por el principio)
 */
class Pila<T> : Stack<T> {
    // Usamos una lista enlazada para almacenar los elementos
    private val lista = linkedListOf<T>()

    /**
     * Añade un elemento a la pila, siempre al final
     */
    override fun push(element: T) {
        lista.add(element)
    }

    /**
     * Elimina el último elemento de la pila
     * @return Elemento eliminado
     * @throws NoSuchElementException si la pila está vacía
     */
    override fun pop(): T {
        if (isEmpty()) throw NoSuchElementException("Pila vacía")
        return lista.removeAt(lista.size - 1)
    }

    /**
     * Obtiene el último elemento de la pila
     * @return Último elemento
     * @throws NoSuchElementException si la pila está vacía
     */
    override fun peek(): T {
        if (isEmpty()) throw NoSuchElementException("Pila vacía")
        return lista[lista.size - 1]
    }

    override val size: Int = lista.size

    override fun isEmpty(): Boolean = lista.isEmpty()

    override fun toString() = lista.toString()
}