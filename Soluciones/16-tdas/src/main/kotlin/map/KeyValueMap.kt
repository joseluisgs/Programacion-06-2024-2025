package dev.joseluisgs.map

interface MapKeyValue<K, V> {
    fun add(key: K, value: V)
    fun get(key: K): V?
    fun remove(key: K)
    operator fun contains(key: K): Boolean
}