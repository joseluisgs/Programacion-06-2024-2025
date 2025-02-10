package dev.joseluisgs.repositories

import dev.joseluisgs.models.Vehiculo
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class VehiculosRepositoryImpl : VehiculosRepository {
    private val logger = logging()
    private val vehiculos = mutableMapOf<Long, Vehiculo>()
    private var nextId = 1L

    private fun generateId(): Long {
        return nextId++
    }

    override fun findByMatricula(matricula: String): Vehiculo? {
        logger.debug { "Buscando vehículo por matrícula: $matricula" }
        return vehiculos.values.find { it.matricula == matricula }
    }

    override fun deleteLogical(id: Long): Vehiculo? {
        logger.debug { "Borrando lógicamente vehículo con ID: $id" }
        val timeStamp = LocalDateTime.now()
        /*return vehiculos[id]?.let {
            val deletedEntity = it.copy(isDeleted = true, updatedAt = timeStamp)
            vehiculos[id] = deletedEntity
            deletedEntity
        }*/
        if (vehiculos[id] != null) {
            vehiculos[id] = vehiculos[id]!!.apply {
                isDeleted = true
                updatedAt = timeStamp
            }
            return vehiculos[id]
        }
        return null
    }

    override fun getAll(): List<Vehiculo> {
        logger.debug { "Obteniendo todos los vehículos" }
        return vehiculos.values.toList()
    }

    override fun getById(id: Long): Vehiculo? {
        logger.debug { "Obteniendo vehículo por ID: $id" }
        return vehiculos[id]
    }

    override fun save(entity: Vehiculo): Vehiculo {
        logger.debug { "Guardando vehículo: $entity" }
        val timeStamp = LocalDateTime.now()
        val id = generateId()
        val newEntity = entity.copy(id = id, createdAt = timeStamp, updatedAt = timeStamp)
        vehiculos[id] = newEntity
        return newEntity
    }

    override fun update(id: Long, entity: Vehiculo): Vehiculo? {
        logger.debug { "Actualizando vehículo con ID: $id" }
        vehiculos[id] ?: return null
        val timeStamp = LocalDateTime.now()

        vehiculos[id] = entity.apply {
            updatedAt = timeStamp
        }
        return vehiculos[id]
    }

    override fun delete(id: Long): Vehiculo? {
        logger.debug { "Borrando vehículo con ID: $id" }
        return vehiculos.remove(id)
    }

}