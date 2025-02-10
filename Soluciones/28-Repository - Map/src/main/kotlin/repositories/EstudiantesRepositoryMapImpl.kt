package dev.joseluisgs.repositories

import dev.joseluisgs.extensions.*
import dev.joseluisgs.models.Estudiante
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class EstudiantesRepositoryMapImpl : EstudiantesRepository {
    private val log = logging()
    private var estudiantes = mutableMapOf<Long, Estudiante>()
    private var nextId = 1L

    /**
     * Genera un nuevo ID para un estudiante
     * @return Long
     */
    private fun generateId(): Long {
        log.debug { "Generando nuevo ID" }
        return nextId++
    }

    /**
     * Busca estudiantes por una condición
     * @param condition Condición de búsqueda
     * @return List<Estudiante>
     */
    override fun filter(predicate: (Estudiante) -> Boolean): List<Estudiante> {
        log.info { "Buscando estudiantes por condición" }
        return estudiantes.values.filter(predicate)
    }

    /**
     * Calcula la media de notas de un estudiante
     * @param condition Condición de búsqueda
     * @return Double
     */
    override fun averageBy(predicate: (Estudiante) -> Boolean): Double {
        log.info { "Calculando nota media" }
        return estudiantes.values
            .filter(predicate) // Filtramos por la condición
            .map { it.calificacion } // Obtenemos las notas
            .average() // Calculamos la media
    }

    /**
     * Cuenta estudiantes por una condición
     * @param condition Condición de búsqueda
     * @return Int
     */
    override fun countBy(predicate: (Estudiante) -> Boolean): Int {
        log.info { "Contando estudiantes por condición" }
        return estudiantes.values.count(predicate)
    }

    /**
     * Calcula el máximo de un estudiante por una condición
     * @param predicate Condición de búsqueda
     * @param selector Selector de búsqueda
     * @return Double
     */
    override fun maxBy(selector: (Estudiante) -> Double, predicate: (Estudiante) -> Boolean): Estudiante? {
        log.info { "Calculando máximo" }
        return estudiantes.values
            .filter(predicate) // Filtramos por la condición
            .maxByOrNull(selector) // Calculamos el máximo

    }

    /**
     * Calcula el mínimo de un estudiante por una condición
     * @param predicate Condición de búsqueda
     * @param selector Selector de búsqueda
     * @return Double
     */
    override fun minBy(selector: (Estudiante) -> Double, predicate: (Estudiante) -> Boolean): Estudiante? {
        log.info { "Calculando mínimo" }
        return estudiantes.values
            .filter(predicate) // Filtramos por la condición
            .minByOrNull(selector) // Calculamos el mínimo

    }

    /**
     * Ordena estudiantes por una condición
     * @param mode Modo de ordenación
     * @param condition Condición de búsqueda
     * @return List<Estudiante>
     */
    override fun sortedBy(
        mode: ModoOrdenamiento,
        condition: (Estudiante) -> Double
    ): List<Estudiante> {
        log.info { "Ordenando estudiantes" }
        return when (mode) {
            ModoOrdenamiento.ASCENDENTE -> estudiantes.values.sortedBy(condition)
            ModoOrdenamiento.DESCENDENTE -> estudiantes.values.sortedByDescending(condition)
        }
    }

    /**
     * Obtiene todos los estudiantes
     * @return Array<Estudiante>
     */
    override fun findAll(): List<Estudiante> {
        log.info { "Obteniendo todos los estudiantes" }
        return estudiantes.values.toList()
    }

    /**
     * Busca un estudiante por su ID
     * @param id ID del estudiante
     * @return Estudiante?
     */
    override fun findById(id: Long): Estudiante? {
        log.info { "Buscando estudiante por ID" }
        //return estudiantes.values.find { it.id == id } /// NOOOOO!!! (que busca por todos los valores)
        return estudiantes[id]
    }

    /**
     * Guarda un estudiante
     * @param estudiante Estudiante a guardar
     * @return Estudiante
     */
    override fun save(estudiante: Estudiante): Estudiante {
        log.info { "Guardando estudiante" }
        val newEstudiante = estudiante.copy(
            id = generateId(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        estudiantes[newEstudiante.id] = newEstudiante
        return newEstudiante.also { log.info { "✅ Estudiante guardado" } }
    }

    /**
     * Actualiza un estudiante
     * @param id ID del estudiante
     * @param estudiante Estudiante a actualizar
     * @return Estudiante?
     */
    override fun update(id: Long, estudiante: Estudiante): Estudiante? {
        log.info { "Actualizando estudiante" }
        return estudiantes[id]?.let {
            val updatedEstudiante = estudiante.copy(
                id = it.id,
                createdAt = it.createdAt,
                updatedAt = LocalDateTime.now()
            )
            estudiantes[id] = updatedEstudiante
            updatedEstudiante.also { log.info { "🔄 Estudiante actualizado" } }
        }
    }

    /**
     * Borra un estudiante
     * @param id ID del estudiante
     * @param logical Borrado lógico, por defecto falso, lo que borra de manera física
     * @return Estudiante?
     */
    override fun delete(id: Long, logical: Boolean): Estudiante? {
        log.info { "Borrando estudiante" }
        return if (logical) {
            estudiantes[id]?.let {
                val deletedEstudiante = it.copy(isDeleted = true, updatedAt = LocalDateTime.now())
                estudiantes[id] = deletedEstudiante
                return estudiantes[id]
            }
        } else {
            val removed = estudiantes.remove(id)
            removed?.copy(isDeleted = true, updatedAt = LocalDateTime.now())
        }.also { log.info { "🔴 Estudiante borrado" } }

    }

}