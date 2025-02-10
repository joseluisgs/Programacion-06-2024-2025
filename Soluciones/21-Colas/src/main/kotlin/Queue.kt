package dev.joseluisgs

interface Queue<T> {
    fun enqueue(element: T)
    fun dequeue(): T?
    fun peek(): T?
    fun isEmpty(): Boolean
    fun size(): Int
}