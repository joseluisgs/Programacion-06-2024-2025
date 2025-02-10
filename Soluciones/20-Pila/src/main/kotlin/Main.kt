fun main() {
    val pila = Pila<Int>()

    pila.push(1)
    pila.push(2)
    pila.push(3)
    println(pila.peek())
    println(pila.pop())

    while (!pila.isEmpty()) {
        println(pila.pop())
    }

    println(pila.isEmpty())
}