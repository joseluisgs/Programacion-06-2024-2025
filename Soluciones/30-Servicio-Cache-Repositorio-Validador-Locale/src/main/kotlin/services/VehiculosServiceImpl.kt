package dev.joseluisgs.services

import dev.joseluisgs.cache.Cache
import dev.joseluisgs.exceptions.VehiculoException
import dev.joseluisgs.models.Vehiculo
import dev.joseluisgs.repositories.VehiculosRepository
import dev.joseluisgs.validators.VehiculoValidator
import org.lighthousegames.logging.logging

class VehiculosServiceImpl(
    private val repository: VehiculosRepository,
    private val validator: VehiculoValidator,
    private val cache: Cache<Long, Vehiculo>
) : VehiculosService {
    private val logger = logging()
    override fun getAll(): List<Vehiculo> {
        logger.info { "Obteniendo todos los vehículos" }
        return repository.getAll()
    }

    override fun getById(id: Long): Vehiculo {
        logger.info { "Obteniendo vehículo por ID: $id" }
        // Se lo preguntamos al cache primero
        var vehiculo = cache[id]
        // Si existe en cache lo devolvemos
        if (vehiculo != null) {
            logger.info { "Vehículo encontrado en cache: $vehiculo" }
            return vehiculo
        } else {
            // Si no lo está, lo buscamos en el repositorio y lo añadimos al cache (si existe)
            vehiculo = repository.getById(id)
            if (vehiculo != null) {
                logger.info { "Vehículo encontrado en base de datos: $vehiculo" }
                cache[id] = vehiculo
                return vehiculo
            } else {
                // Si no existe, lanzamos una excepción
                logger.error { "Vehículo no encontrado en repositorio" }
                throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
            }
        }

        /*cache[id]?.let {
            logger.info { "Vehículo encontrado en cache: $it" }
            return it
        } ?: run {
            val vehiculo = repository.getById(id)
            vehiculo?.let {
                logger.info { "Vehículo encontrado en base de datos: $it" }
                cache[id] = it
                return it
            } ?: run {
                logger.error { "Vehículo no encontrado en repositorio" }
                throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
            }
        }*/
        /*return cache.getOrPut(id) {
            repository.getById(id) ?: throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
        }*/
    }

    override fun getByMatricula(matricula: String): Vehiculo {
        logger.info { "Obteniendo vehículo por matrícula: $matricula" }
        return repository.findByMatricula(matricula)
            ?: throw VehiculoException.NotFoundException("Vehículo no encontrado con matrícula: $matricula")


        /* val vehiculo = repository.findByMatricula(matricula)
         if (vehiculo == null) {
             logger.error { "Vehículo no encontrado con matrícula: $matricula" }
             throw VehiculoException.NotFoundException("Vehículo no encontrado con matrícula: $matricula")
         } else {
             // meto el vehículo en cache
             cache[vehiculo.id] = vehiculo
             return vehiculo
         }*/
    }

    override fun save(vehiculo: Vehiculo): Vehiculo {
        logger.info { "Guardando vehículo: $vehiculo" }
        validator.validate(vehiculo)
        // Existe la matricula, excepción
        if (repository.findByMatricula(vehiculo.matricula) != null) {
            logger.error { "Vehículo con matrícula ${vehiculo.matricula} ya existe" }
            throw VehiculoException.AlreadyExistsException("Vehículo con matrícula ${vehiculo.matricula} ya existe")
        }
        val newVehiculo = repository.save(vehiculo)
        cache[newVehiculo.id] = newVehiculo // No es obligatorio actualizar el cache después de guardar
        return newVehiculo
    }

    override fun update(id: Long, vehiculo: Vehiculo): Vehiculo {
        logger.info { "Actualizando vehículo con ID: $id" }
        validator.validate(vehiculo)

        // El problema de la matricula, si existe otro vehículo con la misma matrícula debo ser yo, coincide el id
        val vehiculoByMatricula = repository.findByMatricula(vehiculo.matricula)
        if (vehiculoByMatricula != null && vehiculoByMatricula.id != id) {
            logger.error { "Vehículo con matrícula ${vehiculo.matricula} ya existe" }
            throw VehiculoException.AlreadyExistsException("Vehículo con matrícula ${vehiculo.matricula} ya existe")
        }


        val updatedVehiculo = repository.update(id, vehiculo)
        if (updatedVehiculo == null) {
            logger.error { "Vehículo no encontrado para actualizar" }
            throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
        }
        cache[id] = updatedVehiculo // No es obligatorio actualizar el cache después de actualizar
        return updatedVehiculo
    }

    override fun delete(id: Long): Vehiculo {
        logger.info { "Borrando vehículo con ID: $id" }
        /*val deletedVehiculo = repository.delete(id)
        if (deletedVehiculo == null) {
            logger.error { "Vehículo no encontrado para borrar" }
            throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
        }
        cache.remove(id) // No es obligatorio actualizar el cache después de borrar
        return deletedVehiculo*/
        return repository.delete(id) ?: throw VehiculoException.NotFoundException("Vehículo no encontrado con ID: $id")
            .also {
                cache.remove(id) // No es obligatorio actualizar el cache después de borrar
            }
    }
}