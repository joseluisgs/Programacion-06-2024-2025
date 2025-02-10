package dev.joseluisgs

fun main() {
    val cola = Cola<Int>()
    cola.enqueue(1)
    cola.enqueue(2)
    cola.enqueue(3)

    println(cola.peek())
    println(cola.dequeue())

    while (!cola.isEmpty()) {
        println(cola.dequeue())
    }

    println(cola.isEmpty())
}