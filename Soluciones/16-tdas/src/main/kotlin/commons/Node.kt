package dev.joseluisgs.commons

/**
 * Nodo de una lista
 * @param T Tipo de dato del nodo
 * @property value Valor del nodo
 * @property next Siguiente nodo
 * @constructor Crea un nodo con un valor y un siguiente nodo
 * @see List
 */
data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return value.toString()
    }
}