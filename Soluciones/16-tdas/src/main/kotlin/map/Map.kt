package dev.joseluisgs.map

import dev.joseluisgs.commons.MapNode

fun <K, V> mapOf(vararg pairs: Pair<K, V>): Map<K, V> {
    val map = Map<K, V>()
    pairs.forEach { pair ->
        map.add(pair.first, pair.second)
    }
    return map
}

class Map<K, V> : MapKeyValue<K, V> {
    private var head: MapNode<K, V>? = null

    /**
     * Añadir un nuevo par clave-valor
     * @param key Clave
     * @param value Valor
     */
    override fun add(key: K, value: V) {
        if (head == null) {
            head = MapNode(key, value)
        } else {
            var current = head
            var found = false
            while (current != null) {
                if (current.key == key) {
                    current.value = value
                    found = true
                    break
                }
                if (current.next == null) break
                current = current.next
            }
            if (!found) {
                current?.next = MapNode(key, value)
            }
        }
    }


    /**
     * Obtener el valor de una clave
     * @param key Clave
     * @return Valor asociado a la clave
     */
    override fun get(key: K): V? {
        var current = head
        while (current != null) {
            if (current.key == key) {
                return current.value
            }
            current = current.next
        }
        return null
    }

    /**
     * Eliminar una clave-valor
     * @param key Clave
     */
    override fun remove(key: K) {
        if (head == null) return

        if (head?.key == key) {
            head = head?.next
            return
        }

        var current = head
        var previous: MapNode<K, V>? = null

        while (current != null && current.key != key) {
            previous = current
            current = current.next
        }

        if (current != null) {
            previous?.next = current.next
        }
    }

    /**
     * Devolver el mapa como cadena
     */
    override fun toString(): String {
        val sb = StringBuilder()
        var current = head

        while (current != null) {
            sb.append("[${current.key}, ${current.value}]")
            if (current.next != null) {
                sb.append(", ")
            }
            current = current.next
        }

        return sb.toString()
    }

    /**
     * Comprobar si una clave existe
     * @param key Clave
     * @return Si la clave existe
     */
    override operator fun contains(key: K): Boolean {
        var current = head
        while (current != null) {
            if (current.key == key) {
                return true
            }
            current = current.next
        }
        return false
    }
}