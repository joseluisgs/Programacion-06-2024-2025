package dev.joseluisgs.services

import dev.joseluisgs.models.Vehiculo

interface VehiculosService {
    fun getAll(): List<Vehiculo>
    fun getById(id: Long): Vehiculo
    fun getByMatricula(matricula: String): Vehiculo
    fun save(vehiculo: Vehiculo): Vehiculo
    fun update(id: Long, vehiculo: Vehiculo): Vehiculo
    fun delete(id: Long): Vehiculo
}
