package dev.joseluisgs.repositories

import dev.joseluisgs.extensions.*
import dev.joseluisgs.models.Estudiante
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class EstudiantesRepositoryImpl : EstudiantesRepository {
    private val log = logging()
    private var estudiantes = arrayOfNulls<Estudiante>(10)
    private var nextId = 1L
    private var maxEstudiantes = 10

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
     * @return Array<Estudiante>
     */
    override fun filterBy(predicate: (Estudiante) -> Boolean): Array<Estudiante> {
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
     * @return Array<Estudiante>
     */
    override fun sortedBy(
        mode: ModoOrdenamiento,
        condition: (Estudiante) -> Double
    ): Array<Estudiante> {
        log.info { "Ordenando estudiantes" }
        return estudiantes.sortedBy(mode, condition)
    }

    /**
     * Obtiene todos los estudiantes
     * @return Array<Estudiante>
     */
    override fun findAll(): Array<Estudiante> {
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

        // si hay espacio guardamos
        var pos = estudiantes.indexOf { it == null }
        if (pos != -1) {
            estudiantes[pos] = newEstudiante
        } else {
            log.error { "No hay espacio para guardar el estudiante" }
            redimensionar(modo = ModoRedimension.AUMENTAR)
            pos = estudiantes.indexOf { it == null }
            estudiantes[pos] = newEstudiante
        }
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
        // val pos = this.findById(id)
        /*if (pos != null) {
            val newEstudiante = estudiante.copy(
                id = pos.id,
                createdAt = pos.createdAt,
                updatedAt = LocalDateTime.now()
            )
            estudiantes[indexOf(pos)] = newEstudiante
            return newEstudiante
        }*/
        return this.findById(id)?.let {
            val newEstudiante = estudiante.copy(updatedAt = LocalDateTime.now())
            val index = estudiantes.indexOf { it?.id == id }
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
            estudiantes[estudiantes.indexOf { it?.id == id }] = null
            estudiante.copy(isDeleted = true).also {
                // Redimensionamos si hace falta
                redimensionarSiHaceFalta()
                log.info { "🔴 Estudiante borrado" }
            }
        }

    }

    /**
     * Redimensiona el array de estudiantes si hace falta
     * @see redimensionar
     */
    private fun redimensionarSiHaceFalta() {
        log.debug { "Redimensionando si hace falta" }
        val count = estudiantes.count { true } // Contamos los que no son nulos
        if (count < maxEstudiantes / 2) {
            log.error { "Redimensionando por debajo del 50%" }
            redimensionar(modo = ModoRedimension.DISMINUIR)
        }
    }

    /**
     * Redimensiona el array de estudiantes
     * @param modo Modo de redimensión
     * @see ModoRedimension
     */
    private fun redimensionar(modo: ModoRedimension = ModoRedimension.AUMENTAR) {
        log.debug { "Redimensionando el array de estudiantes modo: ${modo.name}" }
        if (modo == ModoRedimension.DISMINUIR) {
            maxEstudiantes /= 2
            estudiantes = estudiantes.redimensionar(ModoRedimension.DISMINUIR, maxEstudiantes)
        } else {
            maxEstudiantes *= 2
            estudiantes = estudiantes.redimensionar(ModoRedimension.AUMENTAR, maxEstudiantes)
        }
        log.debug { "Array redimensionado con ${estudiantes.size}" }
    }


}