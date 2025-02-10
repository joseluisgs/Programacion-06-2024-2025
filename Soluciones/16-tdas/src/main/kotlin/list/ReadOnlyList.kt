package dev.joseluisgs.list

import dev.joseluisgs.commons.Node
import dev.joseluisgs.extensions.Collection

/**
 * Lista enlazada
 */

/**
 * Función para crear una lista enlazada
 * @param elements Elementos de la lista
 * @return Lista enlazada
 */
/**
 * Función para crear una lista enlazada de solo lectura
 * @param elements Elementos de la lista
 * @return Lista enlazada de solo lectura
 */
fun <T : Any> readOnlyListOf(vararg elements: T): ReadOnlyList<T> {
    val list = ReadOnlyList<T>()
    elements.forEach { element ->
        list.addInternal(element)
    }
    return list
}

class ReadOnlyList<T> : InmutableList<T>, Collection<T> {

    private var head: Node<T>? = null

    // Método interno para añadir elementos, que es interno? solo se puede usar en el mismo paquete
    /**
     * Añade un elemento al final de la lista
     * @param element Elemento a añadir
     */
    internal fun addInternal(element: T) {
        if (head == null) {
            head = Node(element)
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(element)
        }
    }

    /**
     * Obtiene el elemento en un índice específico
     * @param index Índice del elemento
     * @return Elemento en el índice
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    override operator fun get(index: Int): T {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
        // Recorremos la lista hasta el nodo del índice y devolvemos su valor
        var current = head
        repeat(index) {
            current = current?.next
        }
        return current!!.value
    }

    /**
     * Obtiene el tamaño de la lista
     */
    override val size: Int
        get() {
            // Recorremos la lista contando los nodos
            var current = head
            var count = 0
            while (current != null) {
                count++
                current = current.next
            }
            return count
        }

    /**
     * Comprueba si la lista está vacía
     * @return Si la lista está vacía
     */
    override fun isEmpty(): Boolean {
        return head == null
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var current = head
            override fun hasNext(): Boolean = current != null
            override fun next(): T {
                val value = current!!.value
                current = current!!.next
                return value
            }
        }
    }

    /**
     * Comprueba si la lista contiene un elemento
     * @param element Elemento a buscar
     * @return Si la lista contiene el elemento
     */
    override operator fun contains(element: T): Boolean {
        // Recorremos la lista buscando el nodo con el valor
        var current = head
        while (current != null) {
            if (current.value == element) {
                return true
            }
            current = current.next
        }
        return false
    }

    /**
     * Obtiene una representación en cadena de la lista
     * @return Representación en cadena de la lista
     */
    override fun toString(): String {
        // Recorremos la lista concatenando los valores de los nodos
        var current = head
        var result = ""
        while (current != null) {
            // si el siguiente es null, no añadimos la flecha
            result += "${current.value}" + if (current.next != null) ", " else ""
            current = current.next
        }
        return result + ""
    }
}