package dev.joseluisgs

import java.util.*

fun main() {
    val p = Persona("José Luis", 29, "12345678Z", 1000.0)
    val p2 = Persona("Ana", 22, "12345678Z", 800.0)
    val p3 = Persona("José Luis", 29, "12345678Z", 1000.0)

    val personasLista = listOf(p, p, p, p, p, p, p, p2, p3)
    println("Con list:")
    personasLista.forEach { println(it) }

    println("Con Set:")
    val personasSet = setOf(p, p, p, p, p, p, p, p2, p3)
    personasSet.forEach { println(it) }

    val personasHashSet = HashSet<Persona>()
    personasHashSet.add(p)
    personasHashSet.add(p)
    personasHashSet.add(p2)
    personasHashSet.add(p3)

    println("Con HashSet:")
    personasHashSet.forEach { println(it) }


    val personasTreeSet = TreeSet<Persona> { p1, p2 -> p1.edad.compareTo(p2.edad) }
    personasTreeSet.add(p)
    personasTreeSet.add(p)
    personasTreeSet.add(p2)
    personasTreeSet.add(p3)

    println("Con TreeSet:")
    personasTreeSet.forEach { println(it) }
}