package dev.joseluisgs

fun String.repeatTimes(n: Int): String {
    val result = StringBuilder()
    for (i in 0 until n) {
        result.append(this)
    }
    return result.toString()
}

operator fun String.times(n: Int): String {
    val result = StringBuilder()
    for (i in 0 until n) {
        result.append(this)
    }
    return result.toString()
}

fun main() {
    val repeatedString = "Hello, World! ".repeatTimes(3)
    println(repeatedString)
    val timesString = "Hello, World! " * 3
    println(timesString)

    val fraccion1 = Fraccion(1, 2)
    val fraccion2 = Fraccion(1, 4)
    val fraccionSuma = fraccion1 + fraccion2
    println(fraccionSuma)
    val fraccionResta = fraccion1 - fraccion2
    println(fraccionResta)
    val fraccionMultiplicacion = fraccion1 * fraccion2
    println(fraccionMultiplicacion)
    val fraccionDivision = fraccion1 / fraccion2
    println(fraccionDivision)
    val fraccionNegativa = -fraccion1
    println(fraccionNegativa)
    val fraccionComparacion = fraccion1 == fraccion2
    println(fraccionComparacion)
    val fraccionComparacion2 = fraccion1 > fraccion2
    println(fraccionComparacion2)
    val fraccionComparacion3 = fraccion1 <= fraccion2
    println(fraccionComparacion3)
}