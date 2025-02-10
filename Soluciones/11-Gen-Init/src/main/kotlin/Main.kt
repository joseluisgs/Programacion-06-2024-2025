package dev.joseluisgs

import dev.joseluisgs.models.Box
import dev.joseluisgs.models.Estudiante
import dev.joseluisgs.models.Pair
import dev.joseluisgs.models.Triple

fun main() {
    val boxString = Box<String>("Hola")
    println(boxString)
    println(boxString.get())
    boxString.put("Adios")
    println(boxString.get())

    val boxInt = Box<Int>(10)
    println(boxInt)
    println(boxInt.get())
    boxInt.put(20)
    println(boxInt.get())

    val boxDouble = Box<Double>(10.0)
    println(boxDouble)
    println(boxDouble.get())
    boxDouble.put(20.0)
    println(boxDouble.get())

    val boxBoolean = Box<Boolean>(true)
    println(boxBoolean)
    println(boxBoolean.get())
    boxBoolean.put(false)
    println(boxBoolean.get())

    val boxArray = Box<Array<Int>>(arrayOf(1, 2, 3))
    println(boxArray)
    println(boxArray.get().joinToString())
    boxArray.put(arrayOf(4, 5, 6))
    println(boxArray.get().joinToString())

    val boxOfBoxString = Box<Box<String>>(Box("Hola"))
    println(boxOfBoxString)
    println(boxOfBoxString.get().get())
    boxOfBoxString.get().put("Adios")
    println(boxOfBoxString.get().get())

    val boxEstudiante = Box<Estudiante>(Estudiante("Pepe", 5.0))
    println(boxEstudiante)
    println("${boxEstudiante.get().nombre} - ${boxEstudiante.get().calificacion}")
    boxEstudiante.put(Estudiante("Juan", 7.0))
    println(boxEstudiante.get())

    val parEnteroEstudiante = Pair<Int, Estudiante>(10, Estudiante("Pepe", 5.0))
    println(parEnteroEstudiante)
    println("${parEnteroEstudiante.first} - ${parEnteroEstudiante.second}")

    val tripleEnteroEstudiante = Triple<Int, Estudiante, String>(10, Estudiante("Pepe", 5.0), "Hola")
    println(tripleEnteroEstudiante)
    println("${tripleEnteroEstudiante.first} - ${tripleEnteroEstudiante.second} - ${tripleEnteroEstudiante.third}")

    val tripleBoxStringPairIntEstudiante =
        Triple<Box<String>, Pair<Int, Estudiante>, String>(Box("Hola"), Pair(10, Estudiante("Pepe", 5.0)), "Adios")
    println(tripleBoxStringPairIntEstudiante)
    println("${tripleBoxStringPairIntEstudiante.first.get()} - ${tripleBoxStringPairIntEstudiante.second.first} - ${tripleBoxStringPairIntEstudiante.second.second} - ${tripleBoxStringPairIntEstudiante.third}")
}