package dev.joseluisgs.extensions

import dev.joseluisgs.list.LinkedList

fun <T> Collection<T>.forEach(action: (T) -> Unit) {
    for (element in this) {
        action(element)
    }
}

fun <T> Collection<T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    var index = 0
    for (element in this) {
        action(index++, element)
    }
}

fun <T> Collection<T>.filter(predicate: (T) -> Boolean): Collection<T> {
    val result = LinkedList<T>()
    for (element in this) {
        if (predicate(element)) {
            result.add(element)
        }
    }
    return result
}

fun <T, R> Collection<T>.map(transform: (T) -> R): Collection<R> {
    val result = LinkedList<R>() // O un LinkedSet si quieres mantener unicidad
    for (element in this) {
        result.add(transform(element))
    }
    return result
}

fun <T> Collection<T>.findOrNull(predicate: (T) -> Boolean): T? {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    return null
}

fun <T> Collection<T>.count(predicate: (T) -> Boolean = { true }): Int {
    var count = 0
    for (element in this) {
        if (predicate(element)) {
            count++
        }
    }
    return count
}

fun <T> Collection<T>.firstOrNull(predicate: (T) -> Boolean = { true }): T? {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    return null
}

fun <T> Collection<T>.lastOrNull(predicate: (T) -> Boolean = { true }): T? {
    var lastMatch: T? = null
    for (element in this) {
        if (predicate(element)) {
            lastMatch = element
        }
    }
    return lastMatch
}

fun <T, R : Comparable<R>> Collection<T>.maxOrNull(selector: (T) -> R?): T? {
    var maxElement: T? = null
    var maxValue: R? = null
    for (element in this) {
        val value = selector(element)
        if (value != null && (maxValue == null || value > maxValue)) {
            maxValue = value
            maxElement = element
        }
    }
    return maxElement
}

fun <T, R : Comparable<R>> Collection<T>.minOrNull(selector: (T) -> R?): T? {
    var minElement: T? = null
    var minValue: R? = null
    for (element in this) {
        val value = selector(element)
        if (value != null && (minValue == null || value < minValue)) {
            minValue = value
            minElement = element
        }
    }
    return minElement
}

fun <T> Collection<T>.sumBy(selector: (T) -> Number?): Double {
    var total = 0.0
    for (element in this) {
        selector(element)?.toDouble()?.let { total += it }
    }
    return total
}

fun <T> Collection<T>.averageBy(selector: (T) -> Number?): Double? {
    var count = 0
    var total = 0.0
    for (element in this) {
        selector(element)?.toDouble()?.let {
            total += it
            count++
        }
    }
    return if (count == 0) null else total / count
}


fun <T> Collection<T>.reduce(operation: (acc: T, T) -> T): T {
    val iterator = this.iterator()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Empty collection can't be reduced.")
    var accumulator = iterator.next()
    while (iterator.hasNext()) {
        accumulator = operation(accumulator, iterator.next())
    }
    return accumulator
}

fun <T, R> Collection<T>.fold(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    for (element in this) {
        accumulator = operation(accumulator, element)
    }
    return accumulator
}

fun <T> Collection<T>.any(predicate: (T) -> Boolean): Boolean {
    for (element in this) {
        if (predicate(element)) {
            return true
        }
    }
    return false
}

fun <T> Collection<T>.all(predicate: (T) -> Boolean): Boolean {
    for (element in this) {
        if (!predicate(element)) {
            return false
        }
    }
    return true
}

fun <T> Collection<T>.none(predicate: (T) -> Boolean): Boolean {
    for (element in this) {
        if (predicate(element)) {
            return false
        }
    }
    return true
}

fun <T> Collection<T>.chunked(size: Int): Collection<Collection<T>> {
    if (size <= 0) throw IllegalArgumentException("Size must be greater than 0")
    val result = LinkedList<Collection<T>>()
    var chunk = LinkedList<T>()
    for (element in this) {
        chunk.add(element)
        if (chunk.size == size) {
            result.add(chunk)
            chunk = LinkedList()
        }
    }
    if (chunk.isEmpty()) result.add(chunk)
    return result
}

fun <T> Collection<T>.takeWhile(predicate: (T) -> Boolean): Collection<T> {
    val result = LinkedList<T>()
    for (element in this) {
        if (!predicate(element)) break
        result.add(element)
    }
    return result
}


fun <T> Collection<T>.take(n: Int): Collection<T> {
    val result = LinkedList<T>()
    var count = 0
    for (element in this) {
        if (count++ == n) break
        result.add(element)
    }
    return result
}

fun <T> Collection<T>.drop(n: Int): Collection<T> {
    val result = LinkedList<T>()
    var count = 0
    for (element in this) {
        if (count++ >= n) {
            result.add(element)
        }
    }
    return result
}



