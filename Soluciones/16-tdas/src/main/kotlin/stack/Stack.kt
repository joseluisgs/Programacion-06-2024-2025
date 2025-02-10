package dev.joseluisgs.stack

interface Stack<T> {
    fun push(element: T)
    fun pop(): T
    fun peek(): T
    val size: Int
    fun isEmpty(): Boolean
}