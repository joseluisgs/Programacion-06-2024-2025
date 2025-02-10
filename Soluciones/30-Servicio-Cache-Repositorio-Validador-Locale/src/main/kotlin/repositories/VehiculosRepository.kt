package dev.joseluisgs.repositories

import dev.joseluisgs.models.Vehiculo

interface VehiculosRepository : CrudRepository<Long, Vehiculo> {
    fun findByMatricula(matricula: String): Vehiculo?
    fun deleteLogical(id: Long): Vehiculo?
}