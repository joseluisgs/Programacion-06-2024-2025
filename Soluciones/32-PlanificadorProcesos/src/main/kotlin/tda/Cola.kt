package dev.joseluisgs

interface Cola<T> {
    fun enqueue(elemento: T)
    fun dequeue(): T?
    fun isEmpty(): Boolean
    fun first(): T?
    fun size(): Int
    override fun toString(): String
}