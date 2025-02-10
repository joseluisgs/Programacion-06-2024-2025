package dev.joseluisgs

infix fun String.repetir(veces: Int): String {
    var resultado = ""
    for (i in 0..veces) {
        resultado += this
    }
    return resultado
}

infix fun String.mayusculas(position: Int): String {
    return this[position].uppercaseChar().toString()
}

infix fun Int.sumar(numero: Int): Int {
    return this + numero
}

infix operator fun Int.plus(numero: Int): Int {
    return this + numero
}


infix fun Int.restar(numero: Int): Int {
    return this - numero
}

fun main() {
    println("Hello World!")
    println("Hello World!" repetir 3)
    println("Hello World!".repetir(3))
    println("Hello World!".mayusculas(3))
    println("Hello World!" mayusculas 3)

    println(1 + 2) // sobrecarga de operadores
    println(1.sumar(2)) // metodos normal de objetos
    println(1 sumar 2) // metodos infix
    println(1 restar 2)
    println(1 - 2)
}