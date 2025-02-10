package dev.joseluisgs

fun main() {
    val p1 = "Se verla al reves"
    val p2 = "Anita lava la tina"

    println(p1)
    println("Iterativo: $p1 es palindromo: ${palindromoIterativo(p1)}")
    println("Funciones: $p1 es palindromo: ${palindromoFunciones(p1)}")
    println("Pilas y Colas: $p1 es palindromo: ${palindromePilasYColas(p1)}")
    println("Pilas y Colas Extensiones: $p1 es palindromo: ${palindromePilasYColasExtensiones(p1)}")

    println()
    println(p2)
    println("Iterativo: $p2 es palindromo: ${palindromoIterativo(p2)}")
    println("Funciones: $p2 es palindromo: ${palindromoFunciones(p2)}")
    println("Pilas y Colas: $p2 es palindromo: ${palindromePilasYColas(p2)}")
    println("Pilas y Colas Extensiones: $p2 es palindromo: ${palindromePilasYColasExtensiones(p2)}")

}

fun palindromoIterativo(palabra: String): Boolean {
    val palabraLimpia = palabra.replace(" ", "").lowercase()
    val longitud = palabraLimpia.length
    for (i in 0 until longitud / 2) {
        if (palabraLimpia[i] != palabraLimpia[longitud - i - 1]) {
            return false
        }
    }
    return true
}

fun palindromoFunciones(palabra: String): Boolean {
    val palabraLimpia = palabra.replace(" ", "").lowercase()
    return palabraLimpia == palabraLimpia.reversed()
}

fun palindromePilasYColas(palabra: String): Boolean {
    val palabraLimpia = palabra.replace(" ", "").lowercase()
    val longitud = palabraLimpia.length
    val pila = mutableListOf<Char>()
    val cola = mutableListOf<Char>()
    for (i in 0 until longitud) {
        pila.add(palabraLimpia[i])
        cola.add(palabraLimpia[longitud - i - 1])
    }
    return pila == cola
}

// funcioens de extensionpara crear una pila en un array list de char
fun MutableList<Char>.push(element: Char) = this.add(element)
fun MutableList<Char>.pop() = this.removeAt(this.size - 1)

// funcioens de extension para crear una cola en un array list de char
fun MutableList<Char>.enqueue(element: Char) = this.add(element)
fun MutableList<Char>.dequeue() = this.removeAt(0)

fun palindromePilasYColasExtensiones(palabra: String): Boolean {
    val palabraLimpia = palabra.replace(" ", "").lowercase()
    val longitud = palabraLimpia.length
    val pila = mutableListOf<Char>()
    val cola = mutableListOf<Char>()
    for (i in 0 until longitud) {
        pila.push(palabraLimpia[i])
        cola.enqueue(palabraLimpia[i])
    }
    return pila == cola
}