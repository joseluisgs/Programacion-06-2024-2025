package dev.joseluisgs.set

import dev.joseluisgs.extensions.Collection
import dev.joseluisgs.list.linkedListOf

/**
 * Set enlazado: implementa una estructura similar a un conjunto donde los elementos son únicos.
 */

/**
 * Función para crear un conjunto enlazado
 * @param elements Elementos del conjunto
 * @return Conjunto enlazado
 */
fun <T> linkedSetOf(vararg elements: T): LinkedSet<T> where T : Any {
    val set = LinkedSet<T>()
    for (element in elements) {
        set.add(element)
    }
    return set
}

/**
 * Función para crear un conjunto enlazado usando una acción
 * @param action Acción para añadir elementos al conjunto
 * @return Conjunto enlazado
 */
fun <T> linkedSetOf(action: LinkedSet<T>.() -> Unit): LinkedSet<T> where T : Any {
    val set = LinkedSet<T>()
    set.action()
    return set
}

class LinkedSet<T> : Set<T>, Collection<T> {

    private val lista = linkedListOf<T>()

    /**
     * Añade un elemento al conjunto, si no está ya presente
     * @param element Elemento a añadir
     */
    override fun add(element: T) {
        // Si no contiene el elemento, lo añadimos
        if (!lista.contains(element)) {
            lista.add(element)
        }
    }

    /**
     * Añade un elemento en una posición concreta
     * @param index Posición donde añadir el elemento
     * @param element Elemento a añadir
     */
    override fun add(index: Int, element: T) {
        // Si no contiene el elemento, lo añadimos
        if (!lista.contains(element)) {
            lista.add(index, element)
        }
    }

    /**
     * Elimina un elemento del conjunto
     * @param element Elemento a eliminar
     * @return true si se elimina, false en caso contrario
     */
    override fun remove(element: T): Boolean {
        return lista.remove(element)
    }

    /**
     * Elimina un elemento del conjunto en una posición concreta
     * @param index Posición donde eliminar el elemento
     * @return Elemento eliminado
     * throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    override fun removeAt(index: Int): T {
        return lista.removeAt(index)
    }

    /**
     * Elimina todos los elementos del conjunto
     */
    override fun clear() {
        lista.clear()
    }

    /**
     * Almacena un elemento en una posición concreta
     * @param index Posición donde almacenar el elemento
     * @param element Elemento a almacenar
     * throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    override operator fun set(index: Int, element: T) {
        if (!lista.contains(element)) {
            lista[index] = element
        }
    }

    /**
     * Devuelve un elemento de una posición concreta
     * @param index Posición del elemento
     * @return Elemento en la posición
     * throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    override operator fun get(index: Int): T {
        return lista[index]
    }

    override val size: Int
        get() = lista.size

    /**
     * Comprueba si el conjunto está vacío
     */
    override fun isEmpty(): Boolean {
        return lista.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return lista.iterator()
    }

    /**
     * Comprueba si un elemento está en el conjunto
     * @param element Elemento a buscar
     * @return true si el elemento está en el conjunto, false en caso contrario
     */
    override operator fun contains(element: T): Boolean {
        return lista.contains(element)
    }

    /**
     * Devuelve la representación en cadena del conjunto
     */
    override fun toString(): String {
        return lista.toString()
    }

}
