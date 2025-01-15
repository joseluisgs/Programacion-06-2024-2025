package dev.joseluisgs

// Funcion
fun sumar(a: Int, b: Int) = a + b

// Funcion Anonima
val suma: (Int, Int) -> Int = fun(a: Int, b: Int) = a + b
val multiply: (Int, Int) -> Int = fun(a: Int, b: Int) = a * b

// Funcion Lambda
val divide: (Int, Int) -> Int = { a: Int, b: Int -> a / b }

// Funcion de orden superior, que recibe funciones como parámetros
fun operar(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

fun main() {
    var resultado = sumar(1, 2)
    println("El resultado es $resultado")
    resultado = suma(1, 2)
    println("El resultado es $resultado")
    var pepe: (Int, Int) -> Int = suma
    resultado = pepe(1, 2)
    println("El resultado es $resultado")
    pepe = multiply
    resultado = pepe(1, 2)
    println("El resultado es $resultado")
    pepe = divide
    resultado = pepe(1, 2)
    println("El resultado es $resultado")
    resultado = divide(1, 2)
    println("El resultado es $resultado")

    resultado = operar(1, 2, suma)
    println("El resultado es $resultado")

    resultado = operar(1, 2, multiply)
    println("El resultado es $resultado")

    resultado = operar(1, 2, divide)
    println("El resultado es $resultado")

    resultado = operar(1, 2, fun(a: Int, b: Int) = a + b)
    println("El resultado es $resultado")

    resultado = operar(1, 2, { a, b -> a + b })
    println("El resultado es $resultado")

    // Cuando la función es el último parámetro, podemos sacarla fuera de los paréntesis
    resultado = operar(1, 2) { a, b -> a + b }
    println("El resultado es $resultado")

    resultado = operar(1, 2) { a, b -> a * b }
    println("El resultado es $resultado")

    resultado = operar(1, 2) { a, b -> a / b }
    println("El resultado es $resultado")

    resultado = operar(1, 2) { a, b -> a % b }
    println("El resultado es $resultado")

    resultado = operar(1, 2) { a, b -> a / b + a % b }
    println("El resultado es $resultado")

    // Podemos poner tantas líneas como queramos,
    // pero el resultado será el de la última
    resultado = operar(1, 2) { a, b ->
        var res = a / b + a % b + a * b + a - b + 500
        res += 1000
        res / 200
        res
    }
    println("El resultado es $resultado")

    repeat(10) {
        println("Hola $it")
    }

    myRepeat(10) {
        println("Hola $it")
    }

    val intArray = intArrayOf(1, 2, 3, 4, 5)

    for (i in intArray) {
        println(i)
    }

    myForEach(intArray) {
        println(it)
    }

    myForEach(intArray) {
        if (it % 2 == 0) {
            println("$it es par")
        }
    }

    val res = myFilter(intArray) {
        it % 2 == 0
    }
    println(res.contentToString())

    val res2 = myFilter(intArray) {
        it % 2 != 0
    }
    println(res2.contentToString())

    val res3 = myFilter(intArray) {
        isPrime(it)
    }
    println(res3.contentToString())

    val res4 = myMap(intArray) {
        it * 2
    }
    println(res4.contentToString())

    val res5 = myMap(intArray) {
        it * 3
    }
    println(res5.contentToString())

    val res6 = myMap(intArray) {
        it + 1
    }
    println(res6.contentToString())

    val res7 = myMap(intArray) {
        it - 1
    }
    println(res7.contentToString())

    val res8 = myMap(intArray) {
        it % 2
    }
    println(res8.contentToString())

    val res9 = myFind(intArray) {
        it % 2 == 0
    }
    println(res9)

    val res10 = myFind(intArray) {
        it % 2 != 0
    }
    println(res10)

    val res11 = myFind(intArray) {
        isPrime(it)
    }
    println(res11)

    val res12 = myIndexOf(intArray) {
        it % 2 == 0
    }
    println(res12)

    val res13 = myIndexOf(intArray) {
        it % 2 != 0
    }
    println(res13)

    val res14 = myIndexOf(intArray) {
        isPrime(it)
    }
    println(res14)

}

// Funciones de orden superior, que reciben funciones como parámetros
// Repetir una acción n veces
fun myRepeat(times: Int, action: (Int) -> Unit) {
    for (index in 0 until times) {
        action(index)
    }
}

// Recorrer un array y aplicar una acción a cada elemento
fun myForEach(intArray: IntArray, action: (Int) -> Unit) {
    for (i in intArray) {
        action(i)
    }
}

// Filtrar un array y aplicar una acción a cada elemento
fun myFilter(intArray: IntArray, action: (Int) -> Boolean): IntArray {
    val result = Array<Int?>(intArray.size) { null }
    var index = 0
    for (i in intArray) {
        if (action(i)) {
            result[index] = i
            index++
        }
    }
    // Quitamos los nulos
    var countNulls = 0
    for (i in result.indices) {
        if (result[i] == null) {
            countNulls++
        }
    }
    val result2 = IntArray(intArray.size - countNulls)
    index = 0
    for (i in result.indices) {
        if (result[i] != null) {
            result2[index] = result[i]!!
            index++
        }
    }
    return result2
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) return false
    if (number == 2) return true
    if (number % 2 == 0) return false
    var i = 3
    while (i * i <= number) {
        if (number % i == 0) return false
        i += 2
    }
    return true
}

fun myMap(intArray: IntArray, action: (Int) -> Int): IntArray {
    val result = IntArray(intArray.size)
    for (i in intArray.indices) {
        result[i] = action(intArray[i])
    }
    return result
}

fun myFind(intArray: IntArray, action: (Int) -> Boolean): Int? {
    for (i in intArray.indices) {
        if (action(intArray[i])) {
            return intArray[i]
        }
    }
    return null
}

fun myIndexOf(intArray: IntArray, action: (Int) -> Boolean): Int {
    for (i in intArray.indices) {
        if (action(intArray[i])) {
            return i
        }
    }
    return -1
}