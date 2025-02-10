package dev.joseluisgs.queue

interface Queue<T> {
    fun enqueue(element: T)
    fun dequeue(): T
    fun peek(): T
    val size: Int
    fun isEmpty(): Boolean
}