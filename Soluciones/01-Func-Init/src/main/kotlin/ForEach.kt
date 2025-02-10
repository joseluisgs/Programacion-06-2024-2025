package dev.joseluisgs

fun forEachElement(collection: IntArray, action: (Int) -> Unit) {
    for (element in collection) {
        action(element)
    }
}