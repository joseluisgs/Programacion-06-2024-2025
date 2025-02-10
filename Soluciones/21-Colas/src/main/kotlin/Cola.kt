package dev.joseluisgs

class Cola<T> : Queue<T> {
    private val elementos = mutableListOf<T>()

    override fun enqueue(element: T) {
        elementos.add(element)
    }

    override fun dequeue(): T? {
        return if (elementos.isNotEmpty())
            elementos.removeAt(0)
        else null
    }

    override fun peek(): T? {
        return if (elementos.isNotEmpty())
            elementos[0]
        else null
    }

    override fun isEmpty(): Boolean {
        return elementos.isEmpty()
    }

    override fun size(): Int {
        return elementos.size
    }
}