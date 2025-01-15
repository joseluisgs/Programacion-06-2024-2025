package dev.joseluisgs

class CalculadoraFuncional {
    val operacion = { a: Int, b: Int, op: (Int, Int) -> Int -> op(a, b) }

    // Con una función de orden superior como parámetro
    fun operar(a: Int, b: Int, op: (Int, Int) -> Int): Int {
        println("Operando $a y $b")
        return op(a, b)
    }
}