package dev.joseluisgs.extensions

interface Collection<T> {
    val size: Int
    fun isEmpty(): Boolean
    operator fun iterator(): Iterator<T>
}