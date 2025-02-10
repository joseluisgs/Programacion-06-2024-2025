package dev.joseluisgs

import java.util.*

fun main() {
    val listOfInts = listOf(1, 2, 3, 4, 5) // Es solo lectura, como está capada en el tipo, es covariante
    val listOfIntsMutable = mutableListOf(1, 2, 3, 4, 5) // Es mutable

    // En ambos casos podemos acceder para leer los elementos por índice
    println(listOfInts[0]) // Acceso a un elemento por índice .get(0)
    println(listOfIntsMutable[0]) // Acceso a un elemento por índice .get(0)

    // si queremos escribir...
    // listOfInts[0] = 10 // No se puede escribir en una lista inmutable .set(0, 10)
    listOfIntsMutable[0] = 10 // Se puede escribir en una lista mutable .set(0, 10)
    println(listOfIntsMutable[0]) // Acceso a un elemento por índice .get(0)

    // Añadir elementos
    listOfIntsMutable.add(6)
    println(listOfIntsMutable)

    // Añadir elementos en una posición
    listOfIntsMutable.add(0, 0)
    println(listOfIntsMutable)

    // Eliminar elementos
    listOfIntsMutable.remove(0)
    println(listOfIntsMutable)

    // Eliminar elementos en una posición
    listOfIntsMutable.removeAt(0)

    // una lista vacía
    val emptyList = emptyList<Int>()
    println(emptyList)

    // una lista vacia mutable
    val emptyListMutable = mutableListOf<Int>()
    println(emptyListMutable)

    // una lista de nulos
    val nullableList = listOf<Int?>(null, null, null)
    println(nullableList)
    // una lista de nulos mutable
    val nullableListMutable = mutableListOf<Int?>(null, null, null)

    // una lista de no nulos
    val nonNullableList = listOfNotNull<Int>(3, null, 4)
    println(nonNullableList)

    // de una lista mutable a a una inmutable
    val listOfInts2 = listOfIntsMutable.toList()

    // de una lista inmutable a a una mutable
    val listOfIntsMutable2 = listOfInts.toMutableList()

    val arrayList = ArrayList<Int>()
    arrayList.add(1)
    arrayList.add(2)

    val linkedList = LinkedList<Int>()
    linkedList.add(1)
    linkedList.add(2)

    val vector = Vector<Int>()
    vector.add(1)
    vector.add(2)

    // Operaciones
    val list = listOf(1, 2, 3, 4, 5)
    println(list.first()) // 1
    println(list.last()) // 5

    list.forEach { println(it) }
    list.forEachIndexed { index, value -> println("El elemento en la posición $index es $value") }

    // Las mías
    list.myForEach { println(it) }
    list.myFilter { it % 2 == 0 }
        .myForEach { println(it) }

    val listOfStrings = listOf("aaaaaa", "bbb", "ccc", "de", "er")
    listOfStrings.myMap { it.length }
        .myFilter { it % 2 == 0 }
        .myForEach { println(it) }


    // Crear una matriz de 5x5 y que no se pueda redimensionar
    val matrix = Array(5) { IntArray(5) }
    // Pero con listas
    val matrix2 = List(5) { MutableList(5) { 0 } }
}

inline fun <T> Iterable<T>.myForEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

inline fun <T> Iterable<T>.myFilter(predicate: (T) -> Boolean): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (predicate(element))
            destination.add(element)
    }
    return destination
}

inline fun <T, R> Iterable<T>.myMap(transform: (T) -> R): List<R> {
    val destination = mutableListOf<R>()
    for (element in this) {
        destination.add(transform(element))
    }
    return destination
}