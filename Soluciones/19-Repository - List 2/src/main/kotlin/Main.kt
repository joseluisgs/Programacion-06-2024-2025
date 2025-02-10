package dev.joseluisgs

import dev.joseluisgs.extensions.ModoOrdenamiento
import dev.joseluisgs.extensions.roundTo
import dev.joseluisgs.factories.EstudianteFactory
import dev.joseluisgs.repositories.EstudiantesRepository
import dev.joseluisgs.repositories.EstudiantesRepositoryImpl

fun main() {
    val repository: EstudiantesRepository = EstudiantesRepositoryImpl()
    val e1 = EstudianteFactory.estudianteRandom()
    println(e1)

    val e = repository.save(e1)
    println(e)

    repeat(20) {
        repository.save(EstudianteFactory.estudianteRandom())
    }

    println("Todos los estudiantes:")
    repository.findAll().forEach {
        println(it)
    }


    println("Estudiantes mayores de 30:")
    repository.filter { it.edad > 30 }.forEach {
        println(it)
        println(it.nombre)
        println(it.calificacion)
    }

    println("Estudiantes con nombre que empieza por J:")
    repository.filter { it.nombre.startsWith("J") }.forEach {
        println(it)
    }

    println("Estudiantes con nombre que empieza por J y son mayores de 30:")
    repository.filter { it.nombre.startsWith("J") && it.edad > 30 }.forEach {
        println(it)
    }

    println("Estudiantes que han aprobado:")
    repository.filter { it.isAprobado }.forEach {
        println(it)
    }

    println("Nota media de los estudiantes:")
    println(repository.averageBy { true }.roundTo(2))

    println("Nota media de los estudiantes que han aprobado:")
    println(repository.averageBy { it.isAprobado }.roundTo(2))

    println("Nota media de los que han sacado mas de un 7:")
    println(repository.averageBy { it.calificacion > 7.0 }.roundTo(2))

    println("Estudiantes que han aprobado:")
    println(repository.countBy { it.isAprobado })

    println("Estudiantes que han suspendido:")
    println(repository.countBy { !it.isAprobado })

    println("Estudiantes que han sacado mas de un 7:")
    println(repository.countBy { it.calificacion > 7.0 })

    println("Actualizamos el estudiante id = 2")
    /*repository.findById(2)?.let {
        println(it)
        repository.update(2, it.copy(nombre = "Updated", calificacion = 0.0))
    } ?: println("No se ha encontrado el estudiante")*/

    repository.findById(2)?.let {
        println(it)
    }

    println("Borramos el estudiante id = 2")
    repository.delete(2)?.let { println(it) } ?: println("No se ha encontrado el estudiante")

    // Borramos a 15
    (10..15).forEach {
        repository.delete(it.toLong())
    }

    println("Todos los estudiantes:")
    repository.findAll().forEach {
        println(it)
    }

    println("Estudiantes con la nota mayor:")
    println(repository.maxBy({ it.calificacion }))

    println("Estudiante con la nota mayor a 6:")
    // println(repository.maxBy({ it.calificacion }, { it.calificacion > 6.0 }))
    println(repository.maxBy({ it.calificacion }) { it.calificacion > 6.0 }) // Otra forma de hacerlo, recuerda que si una lambda es el último argumento, se puede sacar fuera de los paréntesis

    println("Estudiantes con la nota menor:")
    println(repository.minBy({ it.calificacion }))

    println("Estudiante con la nota menor a 6:")
    println(repository.minBy({ it.calificacion }) { it.calificacion < 6.0 })

    println("Estudiantes ordenados por nota ascendente:")
    repository.sortedBy { it.calificacion }.forEach {
        println(it)
    }

    println("Estudiantes ordenados por nota descendente:")
    repository.sortedBy(ModoOrdenamiento.DESCENDENTE) { it.calificacion }.forEach {
        println(it)
    }


}