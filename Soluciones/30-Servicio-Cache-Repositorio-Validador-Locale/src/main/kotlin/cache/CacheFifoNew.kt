package dev.joseluisgs.cache

import org.lighthousegames.logging.logging


class CacheFifoNew<K, V>(private val capacity: Int = CACHE_SIZE) : Cache<K, V> {
    private val logger = logging()

    // Usamos LinkedHashMap con orden de inserción (modo FIFO: acceso = false)
    // LinkedHashMap se configura para mantener el orden de inserción, lo que significa
    // que los elementos se almacenan en el orden en que fueron agregados.
    // Esto evita la necesidad de recorrer el mapa para encontrar el elemento más antiguo
    // y hacer uso de la cola para mantener el orden de inserción.
    // El constructor recibe la capacidad inicial, el factor de carga y un booleano para
    // indicar si debe mantener el orden de acceso. Usamos false para mantener el orden
    // de inserción, implementando así el comportamiento FIFO.
    // Si se accede a un elemento, no se mueve al final, lo que significa que el elemento
    // sigue en la posición en la que fue insertado.
    // por lo tato el primero de la lista será el primero en ser eliminado (ya que fue el primero en insertarse)
    // El factor de carga de 0.75f indica cuándo se debe redimensionar el mapa.
    // El método removeEldestEntry se sobrescribe para eliminar el elemento más antiguo
    // cuando el tamaño de la caché supera la capacidad definida.


    private val cache = object : LinkedHashMap<K, V>(capacity, 0.75f, false) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            // Si el tamaño supera la capacidad, eliminamos el elemento más antiguo en insertarse, es decir
            // el que se encuentra al principio de la lista
            // Que será el primero de la lista
            logger.debug { "Desalojando la clave más antigua: ${entries.first().key}" }
            return size > capacity
        }
    }

    override fun get(key: K): V? {
        logger.debug { "Obteniendo valor de la cache con clave $key" }
        return cache[key]
    }

    override fun set(key: K, value: V): V? {
        logger.debug { "Guardando valor en la cache con clave $key" }
        return cache.put(key, value)
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