package dev.joseluisgs.list

/**
 * Interfaz de lista inmutable
 */
interface InmutableList<T> {
    operator fun get(index: Int): T
    fun contains(element: T): Boolean

}