package dev.joseluisgs.cache

import org.lighthousegames.logging.logging

class CacheLruNew<K, V>(private val capacity: Int = CACHE_SIZE) : Cache<K, V> {
    private val logger = logging()

    // Usamos LinkedHashMap con orden de acceso (modo LRU: acceso = true)
    // LinkedHashMap se configura para mantener el orden basado en el acceso reciente,
    // lo que significa que cada vez que se accede a un elemento, se mueve al final.
    // Esto evita la necesidad de recorrer el mapa para encontrar el elemento menos usado, usando el min.
    // El constructor recibe la capacidad inicial, el factor de carga y un booleano para
    // indicar si debe mantener el orden de acceso. Usamos true para habilitar el orden
    // de acceso, implementando así el comportamiento LRU.
    // Si se accede a un elemento, se mueve al final, lo que significa que el elemento menos
    // recientemente usado se encuentra al principio.
    // Por lo tanto el primero de la lista será el menos recientemente usado (ya que una vez se accede se mueve al final)
    // El factor de carga de 0.75f indica cuándo se debe redimensionar el mapa.
    // El método removeEldestEntry se sobrescribe para eliminar el elemento más antiguo
    // cuando el tamaño de la caché supera la capacidad definida.

    private val cache = object : LinkedHashMap<K, V>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            // Si el tamaño supera la capacidad, eliminamos el elemento más antiguo, es decir
            // el que se encuentra al principio de la lista
            // Que será el primero de la lista
            logger.debug { "Desalojando clave menos recientemente usada: ${entries.first().key}" }
            return size > capacity
        }
    }

    override fun get(key: K): V? {
        logger.debug { "Obteniendo valor de la cache con clave $key" }
        return cache[key] // Acceder a un valor lo mueve al final (más recientemente usado)
    }

    override fun set(key: K, value: V): V? {
        logger.debug { "Guardando valor en la cache con clave $key" }
        return cache.put(key, value) // Inserta o actualiza y mueve al final si ya existe
    }

    override fun remove(key: K): V? {
        logger.debug { "Eliminando valor de la cache con clave $key" }
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando la cache" }
        cache.clear()
    }

    override fun size(): Int = cache.size

    override fun isEmpty(): Boolean = cache.isEmpty()

    override fun values(): Collection<V> = cache.values

    override fun keys(): Set<K> = cache.keys

    override fun getOrPut(key: K, defaultValue: () -> V): V {
        logger.debug { "Obteniendo valor de la cache con clave $key o añadiendo uno por defecto" }
        return cache.getOrPut(key, defaultValue)
    }

    override fun contains(key: K): Boolean = cache.containsKey(key)
}
