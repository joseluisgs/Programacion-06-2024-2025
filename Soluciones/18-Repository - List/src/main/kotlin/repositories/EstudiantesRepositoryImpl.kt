package dev.joseluisgs.repositories

import dev.joseluisgs.extensions.*
import dev.joseluisgs.models.Estudiante
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class EstudiantesRepositoryImpl : EstudiantesRepository {
    private val log = logging()
    private var estudiantes = mutableListOf<Estudiante>()
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
    override fun filterBy(predicate: (Estudiante) -> Boolean): List<Estudiante> {
        log.info { "Buscando estudiantes por condición" }
        return estudiantes.filterBy(predicate)
    }

    /**
     * Calcula la media de notas de un estudiante
     * @param condition Condición de búsqueda
     * @return Double
     */
    override fun averageBy(predicate: (Estudiante) -> Boolean): Double {
        log.info { "Calculando nota media" }
        return estudiantes.averageBy(predicate)
    }

    /**
     * Cuenta estudiantes por una condición
     * @param condition Condición de búsqueda
     * @return Int
     */
    override fun countBy(predicate: (Estudiante) -> Boolean): Int {
        log.info { "Contando estudiantes por condición" }
        // return estudiantes.countBy { predicate(it) } // Es lo mismo que has leido arriba o en la siguiente linea
        return estudiantes.countBy(predicate)
    }

    /**
     * Calcula el máximo de un estudiante por una condición
     * @param predicate Condición de búsqueda
     * @param selector Selector de búsqueda
     * @return Double
     */
    override fun maxBy(selector: (Estudiante) -> Double, predicate: (Estudiante) -> Boolean): Estudiante? {
        log.info { "Calculando máximo" }
        return estudiantes.maxByOrNull(selector, predicate)

    }

    /**
     * Calcula el mínimo de un estudiante por una condición
     * @param predicate Condición de búsqueda
     * @param selector Selector de búsqueda
     * @return Double
     */
    override fun minBy(selector: (Estudiante) -> Double, predicate: (Estudiante) -> Boolean): Estudiante? {
        log.info { "Calculando mínimo" }
        return estudiantes.minByOrNull(selector, predicate)
        // return estudiantes.minByOrNull({ selector(it) }) { predicate(it) } // -> Es lo mismo que has leido arriba
        // return estudiantes.minByOrNull({ selector(it) }) { predicate(it) } // -> Es lo mismo que has leido arriba
        //return estudiantes.minByOrNull(selector) { predicate(it) } // -> Es lo mismo que has leido arriba
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
        return estudiantes.sortedBy(mode, condition)
    }

    /**
     * Obtiene todos los estudiantes
     * @return Array<Estudiante>
     */
    override fun findAll(): List<Estudiante> {
        log.info { "Obteniendo todos los estudiantes" }
        return estudiantes.filterBy { true }
    }

    /**
     * Busca un estudiante por su ID
     * @param id ID del estudiante
     * @return Estudiante?
     */
    override fun findById(id: Long): Estudiante? {
        log.info { "Buscando estudiante por ID" }
        return estudiantes.firstOrNull { it.id == id }
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

        estudiantes.add(newEstudiante)
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
        return this.findById(id)?.let {
            val newEstudiante = estudiante.copy(updatedAt = LocalDateTime.now())
            val index = estudiantes.indexOf { it.id == id }
            estudiantes[index] = newEstudiante
            newEstudiante.also { log.info { "🔶 Estudiante actualizado" } }
        }
    }

    /**
     * Borra un estudiante
     * @param id ID del estudiante
     * @return Estudiante?
     */
    override fun delete(id: Long): Estudiante? {
        log.info { "Borrando estudiante" }
        return this.findById(id)?.let { estudiante ->
            estudiantes.removeAt(estudiantes.indexOf { it.id == id })
            estudiante.copy(isDeleted = true).also {
                log.info { "🔴 Estudiante borrado" }
            }
        }

    }
}