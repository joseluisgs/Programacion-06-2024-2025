package dev.joseluisgs.repositories

import dev.joseluisgs.extensions.ModoOrdenamiento
import dev.joseluisgs.models.Estudiante

interface EstudiantesRepository : CrudRepository<Estudiante, Long> {
    fun filterBy(predicate: (Estudiante) -> Boolean = { true }): Array<Estudiante>
    fun averageBy(predicate: (Estudiante) -> Boolean = { true }): Double
    fun countBy(predicate: (Estudiante) -> Boolean = { true }): Int
    fun maxBy(selector: (Estudiante) -> Double = { 0.0 }, predicate: (Estudiante) -> Boolean = { true }): Estudiante?
    fun minBy(selector: (Estudiante) -> Double = { 0.0 }, predicate: (Estudiante) -> Boolean = { true }): Estudiante?
    fun sortedBy(
        mode: ModoOrdenamiento = ModoOrdenamiento.ASCENDENTE,
        condition: (Estudiante) -> Double
    ): Array<Estudiante>
}