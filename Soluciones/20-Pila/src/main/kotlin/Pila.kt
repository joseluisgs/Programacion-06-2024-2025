class Pila<T> : Stack<T> {
    private val elementos = mutableListOf<T>()

    override fun push(element: T) {
        elementos.add(element)
    }

    override fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return elementos.removeAt(elementos.size - 1)
    }

    override fun peek(): T? {
        if (isEmpty()) {
            return null
        }
        return elementos[elementos.size - 1]
    }

    override fun isEmpty(): Boolean {
        return elementos.isEmpty()
    }

    override fun size(): Int {
        return elementos.size
    }
}
