package dev.joseluisgs.cache

const val CACHE_SIZE = 5

interface Cache<K, V> {
    operator fun get(key: K): V?
    operator fun set(key: K, value: V): V?
    fun remove(key: K): V?
    fun clear()
    fun size(): Int
    fun isEmpty(): Boolean
    operator fun contains(key: K): Boolean
    fun getOrPut(key: K, defaultValue: () -> V): V
    fun values(): Collection<V>
    fun keys(): Set<K>
}