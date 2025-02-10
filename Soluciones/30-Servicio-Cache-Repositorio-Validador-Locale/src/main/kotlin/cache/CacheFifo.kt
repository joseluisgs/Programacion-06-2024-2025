package dev.joseluisgs.cache


import org.lighthousegames.logging.logging
import java.util.*

class CacheFifo<K, V>(private val capacity: Int = CACHE_SIZE) : Cache<K, V> {
    private val logger = logging()

    private val cache = mutableMapOf<K, V>()
    private val order: Queue<K> = ArrayDeque() // Cola para mantener el orden de inserción

    override fun get(key: K): V? {
        logger.debug { "Obteniendo valor de la cache con clave $key" }
        return cache[key]
    }

    override fun set(key: K, value: V): V? {
        logger.debug { "Guardando valor en la cache con clave $key" }
        if (cache.containsKey(key)) {
            // Si ya existe, actualizamos el valor y no modificamos el orden
            return cache.put(key, value)
        }

        if (cache.size >= capacity) {
            // Eliminar el elemento más antiguo
            val oldestKey = order.poll()
            if (oldestKey != null) {
                cache.remove(oldestKey)
            }
        }
        // Añadir el nuevo elemento
        order.offer(key)
        return cache.put(key, value)
    }


    override fun remove(key: K): V? {
        logger.debug { "Eliminando valor de la cache con clave $key" }
        order.remove(key)
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando la cache" }
        cache.clear()
        order.clear()
    }

    override fun size(): Int = cache.size

    override fun isEmpty(): Boolean = cache.isEmpty()

    override fun values(): Collection<V> = cache.values

    override fun keys(): Set<K> = cache.keys

    override fun getOrPut(key: K, defaultValue: () -> V): V {
        logger.debug { "Obteniendo valor de la cache con clave $key o añadiendo uno por defecto" }
        return cache.getOrPut(key) {
            set(key, defaultValue())
            defaultValue()
        }
    }

    override fun contains(key: K): Boolean = cache.containsKey(key)
}
