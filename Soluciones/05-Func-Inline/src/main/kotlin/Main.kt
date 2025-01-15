package dev.joseluisgs

fun IntArray.find(action: (Int) -> Boolean): Int? {
    for (i in this.indices) {
        if (action(this[i])) {
            return this[i]
        }
    }
    return null
}

inline fun IntArray.findInline(action: (Int) -> Boolean): Int? {
    for (i in this.indices) {
        if (action(this[i])) {
            return this[i]
        }
    }
    return null
}

fun main() {
    val numbers = intArrayOf(1, 2, 3, 4, 5)
    val result = numbers.find { it % 2 == 0 }
    println(result)
    val resultInline = numbers.findInline { it % 2 == 0 }
    println(resultInline)
}