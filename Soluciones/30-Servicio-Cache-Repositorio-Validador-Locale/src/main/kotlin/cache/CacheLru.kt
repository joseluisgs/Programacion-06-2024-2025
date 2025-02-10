package dev.joseluisgs.cache

import org.lighthousegames.logging.logging
import java.util.concurrent.ConcurrentHashMap

data class CacheEntry<V>(val value: V, var timestamp: Long)

class CacheLru<K : Any, V>(private val capacity: Int = CACHE_SIZE) : Cache<K, V> {
    private val logger = logging()

    private val cache = ConcurrentHashMap<K, CacheEntry<V>>(capacity)

    override fun get(key: K): V? {
        logger.debug { "Obteniendo valor de la cache con clave $key" }
        return cache[key]?.also {
            it.timestamp = System.nanoTime()
        }?.value
    }

    override fun set(key: K, value: V): V? {
        logger.debug { "Guardando valor en la cache con clave $key" }
        if (cache.size >= capacity) {
            evictLeastRecentlyUsed()
        }
        val oldValue = cache.put(key, CacheEntry(value, System.nanoTime()))
        return oldValue?.value
    }

    override fun remove(key: K): V? {
        logger.debug { "Eliminando valor de la cache con clave $key" }
        return cache.remove(key)?.value
    }

    override fun clear() {
        logger.debug { "Limpiando la cache" }
        cache.clear()
    }

    override fun size(): Int = cache.size

    override fun isEmpty(): Boolean = cache.isEmpty()

    override fun values(): Collection<V> = cache.values.map { it.value }

    override fun keys(): Set<K> = cache.keys

    override fun getOrPut(key: K, defaultValue: () -> V): V {
        logger.debug { "Obteniendo valor de la cache con clave $key o añadiendo uno por defecto" }
        return get(key) ?: defaultValue().also { set(key, it) }
    }

    override fun contains(key: K): Boolean = cache.containsKey(key)

    private fun evictLeastRecentlyUsed() {
        val lruKey = cache.entries.minByOrNull { it.value.timestamp }?.key
        lruKey?.let {
            cache.remove(it)
            logger.debug { "Desalojando clave menos recientemente usada $it" }
        }
    }
}
