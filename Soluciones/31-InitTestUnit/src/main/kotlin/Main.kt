package dev.joseluisgs

import dev.joseluisgs.services.Calculadora

fun main() {
    val c = Calculadora()
    println(c.sumar(2.0, 3.0))
    println(c.restar(5.0, 3.0))
    println(c.multiplicar(2.0, 3.0))
    println(c.dividir(6.0, 3.0))
}