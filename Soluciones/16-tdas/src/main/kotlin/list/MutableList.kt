package dev.joseluisgs.list

/**
 * Interfaz de lista mutable
 */
interface MutableList<T> {
    fun add(element: T)
    fun add(index: Int, element: T)
    fun remove(element: T): Boolean
    fun removeAt(index: Int): T
    operator fun set(index: Int, element: T)
    fun clear()
}