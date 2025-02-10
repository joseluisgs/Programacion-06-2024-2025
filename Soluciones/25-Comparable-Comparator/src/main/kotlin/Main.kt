package dev.joseluisgs

fun main() {
    val listaPersonas = listOf(
        Persona("Jose", 30),
        Persona("Luis", 20),
        Persona("Ana", 25)
    )

    if (listaPersonas[0] > listaPersonas[1]) {
        println("${listaPersonas[0]} es mayor que ${listaPersonas[1]}")
    } else {
        println("${listaPersonas[0]} es menor que ${listaPersonas[1]}")
    }

    println("Ordenamos la lista de personas por edad")
    val listaOrdenada = listaPersonas.sorted()
    listaOrdenada.forEach { println(it) }

    val comparatorNombre = Comparator<Persona> { p1, p2 ->
        p1.nombre.compareTo(p2.nombre)
    }

// comparator por edad descendente
    val comparatorEdad = Comparator<Persona> { p1, p2 ->
        p2.edad.compareTo(p1.edad)
    }

    println("Ordenamos la lista de personas por nombre")
    val listaOrdenadaNombre = listaPersonas.sortedWith(comparatorNombre)
    listaOrdenadaNombre.forEach { println(it) }

    println("Ordenamos la lista de personas por edad descendente")
    val listaOrdenadaEdad = listaPersonas.sortedWith(comparatorEdad)
    listaOrdenadaEdad.forEach { println(it) }

    val listaOrdenadaEdadDescendente = listaPersonas.sortedWith { p1, p2 -> p2.edad.compareTo(p1.edad) }
    listaOrdenadaEdadDescendente.forEach { println(it) }

    val listaOrdenadaEdadDescendente2 = listaPersonas.sortedBy { it.edad } // Hace el comparator
    listaOrdenadaEdadDescendente2.forEach { println(it) }
}