package dev.joseluisgs

// Función que suma dos números
fun suma(a: Int, b: Int) = a + b

// Función anónima
val add = fun(a: Int, b: Int): Int = a + b

// Función que suma dos números con lambda
val sum: (Int, Int) -> Int = { a, b -> a + b }

val operar = { a: Int, b: Int, op: (Int, Int) -> Int -> op(a, b) }


fun main() {

    println(suma(2, 3))
    println(add(2, 3))
    println(sum(2, 3))
    println(sum(2, 3))
    println(operar(2, 3, sum))
    println(operar(2, 3, { a, b -> a - b }))
    // Cuando la lambda es el último parámetro, podemos sacarla fuera de los paréntesis
    println(operar(2, 3) { a, b -> a * b })

    val calculadoraNormal = CalculadoraNormal()
    println(calculadoraNormal.sumar(2, 3))
    println(calculadoraNormal.restar(2, 3))
    println(calculadoraNormal.multiplicar(2, 3))
    println(calculadoraNormal.dividir(2, 3))
    // ¿Que pasa si necesitamos agregar más funcionalidad en la calase CalculadoraNormal?

    val calculadoraFuncional = CalculadoraFuncional()
    println(calculadoraFuncional.operar(2, 3, { a, b -> a + b }))
    println(calculadoraFuncional.operar(2, 3, { a, b -> a - b }))
    println(calculadoraFuncional.operar(2, 3, { a, b -> a * b }))
    println(calculadoraFuncional.operar(2, 3, { a, b -> a / b }))
    // ¿Que pasa si necesitamos agregar más funcionalidad en la calase CalculadoraFuncional?
    println(calculadoraFuncional.operar(3, 2, { a, b -> a % b }))
    println(calculadoraFuncional.operar(3, 2) { a, b -> a * a + b * b })
    println(calculadoraFuncional.operar(3, 2) { a, b -> a + 2 * a - b * b - 2 })

    repeat(10) {
        println("Hola $it")
    }


    // myRepeat(10, { println("Hola") })
    myRepeat(10) { i -> println("Hola $i") }

    val array = intArrayOf(1, 2, 3, 4, 5)

    for (element in array) {
        println(element)
    }

    forEachElement(array) {
        println(it)
    }

    val persona = Persona("Pepe", 30) { println("Hola soy $it") }
    // Ejecutamos acción
    println(persona)
    persona.accion()

    val boton: Boton = Boton()
    boton.text = "Aceptar"
    println(boton.text)
    boton.onClick({ println("Botón pulsado") })
    boton.onClick { println("Botón pulsado") }

}