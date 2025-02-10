package dev.joseluisgs.repositories

import dev.joseluisgs.models.Estudiante

interface CrudRepository {
    fun findAll(): Array<Estudiante>
    fun findById(id: Long): Estudiante?
    fun save(estudiante: Estudiante): Estudiante
    fun update(id: Long, estudiante: Estudiante): Estudiante?
    fun delete(id: Long): Estudiante?
}