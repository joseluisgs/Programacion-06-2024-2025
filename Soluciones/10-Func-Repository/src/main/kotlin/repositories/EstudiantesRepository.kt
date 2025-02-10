package dev.joseluisgs.repositories

import dev.joseluisgs.extensions.ModoOrdenamiento
import dev.joseluisgs.models.Estudiante

interface EstudiantesRepository : CrudRepository {
    fun findBy(condition: (Estudiante) -> Boolean): Array<Estudiante>
    fun average(condition: (Estudiante) -> Double): Double
    fun count(condition: (Estudiante) -> Boolean): Int
    fun maxBy(condition: (Estudiante) -> Double): Estudiante?
    fun minBy(condition: (Estudiante) -> Double): Estudiante?
    fun sortedBy(
        mode: ModoOrdenamiento = ModoOrdenamiento.ASCENDENTE,
        condition: (Estudiante) -> Double
    ): Array<Estudiante>
}