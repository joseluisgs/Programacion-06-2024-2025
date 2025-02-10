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
fun <T> linkedListOf(vararg elements: T): LinkedList<T> {
    val list = LinkedList<T>()
    for (element in elements) {
        list.add(element)
    }
    return list
}

/**
 * Función para crear una lista enlazada
 * @param action Acción para añadir elementos a la lista
 * @return Lista enlazada
 */
fun <T> linkedListOf(action: LinkedList<T>.() -> Unit): LinkedList<T> {
    val list = LinkedList<T>()
    list.action()
    return list
}

class LinkedList<T> : MutableList<T>, InmutableList<T>, Collection<T> {

    // Clase interna que representa un nodo de la lista
    private var head: Node<T>? = null

    /**
     * Añade un elemento al final de la lista
     * @param element Elemento a añadir
     */
    override fun add(element: T) {
        // Si la lista está vacía, el nuevo nodo es la cabeza
        if (head == null) {
            head = Node(element)
        } else {
            // Si no, recorremos la lista hasta el último nodo y añadimos el nuevo nodo
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(element)
        }
    }

    /**
     * Añade un elemento en un índice específico
     * @param index Índice donde añadir el elemento
     */
    override fun add(index: Int, element: T) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for size $size")
        }
        // Si el índice es 0, añadimos el nuevo nodo al principio
        if (index == 0) {
            head = Node(element, head)
        } else {
            // Si no, recorremos la lista hasta el nodo anterior al índice y añadimos el nuevo nodo
            var current = head
            repeat(index - 1) {
                current = current?.next
            }
            current?.next = Node(element, current?.next)
        }
    }

    /**
     * Elimina un elemento de la lista
     * @param element Elemento a eliminar
     */
    override fun remove(element: T): Boolean {
        // Si la cabeza es el nodo a eliminar, eliminamos la cabeza
        if (head?.value == element) {
            head = head?.next
            return true
        } else {
            // Si no, recorremos la lista hasta el nodo anterior al nodo a eliminar y eliminamos el nodo
            var current = head
            while (current?.next != null) {
                if (current.next?.value == element) {
                    current.next = current.next?.next
                    return true
                }
                current = current.next
            }
        }
        return false
    }

    /**
     * Elimina un elemento de la lista en un índice específico
     * @param index Índice del elemento a eliminar
     */
    override fun removeAt(index: Int): T {
        // Si el índice es es mayor o igual al tamaño de la lista, devolvemos las excepción
        if (index >= size || index < 0) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for size $size")
        }
        // Si el índice es 0, eliminamos la cabeza
        if (index == 0) {
            val removedValue = head?.value
            head = head?.next
            return removedValue!!
        } else {
            // Si no, recorremos la lista hasta el nodo anterior al índice y eliminamos el nodo
            var current = head
            repeat(index - 1) {
                current = current?.next
            }
            val removedValue = current?.next?.value
            current?.next = current?.next?.next
            return removedValue!!
        }
    }

    /**
     * Elimina todos los elementos de la lista
     */
    override fun clear() {
        head = null
    }

    /**
     * Comprueba si un elemento está en la lista
     * @param element Elemento a comprobar
     * @return Si el elemento está en la lista
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango
     */
    override fun set(index: Int, element: T) {
        // Si el índice es es mayor o igual al tamaño de la lista, devolvemos las excepción
        if (index >= size || index < 0) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for size $size")
        }
        // Recorremos la lista hasta el nodo del índice y cambiamos su valor
        var current = head
        repeat(index) {
            current = current?.next
        }
        current?.value = element
    }

    /**
     * Obtiene un elemento de la lista
     * @param index Índice del elemento
     * @return Elemento de la lista
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango
     */
    override fun get(index: Int): T {
        // Si el índice es es mayor o igual al tamaño de la lista, devolvemos las excepción
        if (index >= size || index < 0) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for size $size")
        }
        // Recorremos la lista hasta el nodo del índice y devolvemos su valor
        var current = head
        repeat(index) {
            current = current?.next
        }
        return current!!.value
    }

    /**
     * Devuelve el tamaño de la lista
     * @return Tamaño de la lista
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

    /**
     * Devuelve un iterador para recorrer la lista
     * @return Iterador de la lista
     */
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
     * Comprueba si un elemento está en la lista
     * @param element Elemento a comprobar
     * @return Si el elemento está en la lista
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango
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
     * Obtiene la representación en cadena de la lista
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