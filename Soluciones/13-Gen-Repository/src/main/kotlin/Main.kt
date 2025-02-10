package dev.joseluisgs

import dev.joseluisgs.extensions.ModoOrdenamiento
import dev.joseluisgs.extensions.roundTo
import dev.joseluisgs.factories.EstudianteFactory
import dev.joseluisgs.repositories.EstudiantesRepository
import dev.joseluisgs.repositories.EstudiantesRepositoryImpl

fun main() {
    val repository: EstudiantesRepository = EstudiantesRepositoryImpl()
    val e1 = EstudianteFactory.random()
    println(e1)

    val e = repository.save(e1)
    println(e)

    repeat(20) {
        repository.save(EstudianteFactory.random())
    }

    println("Todos los estudiantes:")
    repository.findAll().forEach {
        println(it)
    }


    println("Estudiantes mayores de 30:")
    repository.findBy { it.edad > 30 }.forEach {
        println(it)
    }

    println("Estudiantes con nombre que empieza por J:")
    repository.findBy { it.nombre.startsWith("J") }.forEach {
        println(it)
    }

    println("Estudiantes con nombre que empieza por J y son mayores de 30:")
    repository.findBy { it.nombre.startsWith("J") && it.edad > 30 }.forEach {
        println(it)
    }

    println("Estudiantes que han aprobado:")
    repository.findBy { it.isAprobado }.forEach {
        println(it)
    }

    println("Nota media de los estudiantes:")
    println(repository.average { it.calificacion }.roundTo(2))

    println("Estudiantes que han aprobado:")
    println(repository.count { it.isAprobado })

    println("Estudiantes que han suspendido:")
    println(repository.count { !it.isAprobado })

    println("Actualizamos el estudiante id = 2")
    repository.findById(2)?.let {
        println(it)
        repository.update(2, it.copy(nombre = "Updated", calificacion = 0.0))
    } ?: println("No se ha encontrado el estudiante")

    repository.findById(2)?.let {
        println(it)
    }

    println("Borramos el estudiante id = 2")
    repository.delete(2)?.let { println(it) } ?: println("No se ha encontrado el estudiante")

    // Borramos a 15
    repeat(15) {
        repository.delete((it + 1).toLong())
    }

    println("Todos los estudiantes:")
    repository.findAll().forEach {
        println(it)
    }

    println("Estudiantes con la nota mayor:")
    println(repository.maxBy { it.calificacion })

    println("Estudiantes con la nota menor:")
    println(repository.minBy { it.calificacion })

    println("Estudiantes ordenados por nota ascendente:")
    repository.sortedBy { it.calificacion }.forEach {
        println(it)
    }

    println("Estudiantes ordenados por nota descendente:")
    repository.sortedBy(ModoOrdenamiento.DESCENDENTE) { it.calificacion }.forEach {
        println(it)
    }


}