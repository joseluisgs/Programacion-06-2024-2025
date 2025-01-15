package dev.joseluisgs

import java.util.*

data class Person(val name: String, val age: Int)

fun Person.greet() {
    println("Hello, my name is $name and I'm $age years old!")
}

fun Person.isAdult() = age >= 18

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Int.isEven() = this % 2 == 0

fun Int.isOdd() = this % 2 != 0

fun Int.isPrime(): Boolean {
    return when {
        this < 2 -> false
        this == 2 -> true
        else -> {
            var i = 2
            while (i * i <= this) {
                if (this % i == 0) return false
                i++
            }
            true
        }
    }
}

fun String.capitalizeWords(): String {
    // Separamos en espacios
    val words = this.split(" ").toTypedArray()
    // Capitalizamos cada palabra
    for (i in words.indices) {
        if (words[i].isNotEmpty()) {
            words[i] = words[i].substring(0, 1).uppercase(Locale.getDefault()) + words[i].substring(1)
        }
    }
    // Volvemos la lista de palabras a una cadena
    return words.joinToString(" ")
}

fun String.removeVowels(): String {
    return this.replace("[aeiouAEIOU]".toRegex(), "")
}

fun String.repeat(n: Int): String {
    val sb = StringBuilder()
    for (i in 0 until n) {
        sb.append(this)
    }
    return sb.toString()
}


fun IntArray.forEach(action: (Int) -> Unit) {
    for (element in this) action(element)
}

fun IntArray.filter(action: (Int) -> Boolean): IntArray {
    val result = Array<Int?>(this.size) { null }
    var index = 0
    for (i in this) {
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
    val result2 = IntArray(this.size - countNulls)
    index = 0
    for (i in result.indices) {
        if (result[i] != null) {
            result2[index] = result[i]!!
            index++
        }
    }
    return result2
}

fun IntArray.map(action: (Int) -> Int): IntArray {
    val result = IntArray(this.size)
    for (i in this.indices) {
        result[i] = action(this[i])
    }
    return result
}

fun IntArray.find(action: (Int) -> Boolean): Int? {
    for (i in this.indices) {
        if (action(this[i])) {
            return this[i]
        }
    }
    return null
}

fun IntArray.indexOf(action: (Int) -> Boolean): Int {
    for (i in this.indices) {
        if (action(this[i])) {
            return i
        }
    }
    return -1
}

fun IntArray.count(action: (Int) -> Boolean): Int {
    var count = 0
    for (i in this.indices) {
        if (action(this[i])) {
            count++
        }
    }
    return count
}


fun main() {
    val person = Person("John Doe", 30)
    println(person)
    person.greet()
    println("Is adult? ${person.isAdult()}")
    println(3.14159265359.round(2))
    println(10.isEven())
    println(11.isOdd())
    println(17.isPrime())
    println(18.isPrime())
    println("hello world".capitalizeWords())

    val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    numbers.forEach { println(it) }
    val evenNumbers = numbers.filter { it.isEven() }
    println(evenNumbers.joinToString())
    val primeNumbers = numbers.filter { it.isPrime() }
    println(primeNumbers.joinToString())
    val strangeNumber = numbers.filter { it % 3 == 0 }
    println(strangeNumber.joinToString())
    val doubledNumbers = numbers.map { it * 2 }
    println(doubledNumbers.joinToString())
    val rareNumber = numbers.map { if (it.isEven()) it * 3 else it }
    println(rareNumber.joinToString())
    val firstEvenNumber = numbers.find { it.isEven() }
    println(firstEvenNumber)
    val firstOddNumber = numbers.find { it.isOdd() }
    println(firstOddNumber)
    val firstPrimeNumber = numbers.find { it.isPrime() }
    println(firstPrimeNumber)
    val firstNumberGreaterThan5 = numbers.find { it > 5 }
    println(firstNumberGreaterThan5)
    val firstNumberGreaterThan50 = numbers.find { it > 50 }
    println(firstNumberGreaterThan50)
    val indexFirstEvenNumber = numbers.indexOf { it.isEven() }
    println(indexFirstEvenNumber)
    val indexFirstOddNumber = numbers.indexOf { it.isOdd() }
    println(indexFirstOddNumber)
    val indexFirstPrimeNumber = numbers.indexOf { it.isPrime() }
    println(indexFirstPrimeNumber)
    val indexFirstNumberGreaterThan5 = numbers.indexOf { it > 5 }
    println(indexFirstNumberGreaterThan5)
    val indexFirstNumberGreaterThan50 = numbers.indexOf { it > 50 }
    println(indexFirstNumberGreaterThan50)
    val countEvenNumbers = numbers.count { it.isEven() }
    println(countEvenNumbers)
    val countOddNumbers = numbers.count { it.isOdd() }
    println(countOddNumbers)
    val countPrimeNumbers = numbers.count { it.isPrime() }
    println(countPrimeNumbers)
    val countNumbersGreaterThan5 = numbers.count { it > 5 }
    println(countNumbersGreaterThan5)
    val countNumbersGreaterThan50 = numbers.count { it > 50 }
    println(countNumbersGreaterThan50)

    println("Hello".repeat(3))
    println("Hello".removeVowels())


}