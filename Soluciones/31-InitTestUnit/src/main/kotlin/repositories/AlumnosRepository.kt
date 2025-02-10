package dev.joseluisgs.repositories

import dev.joseluisgs.models.Alumno

class AlumnosRepository {
    private val alumnos = mutableListOf<Alumno>()

    fun getAll(): List<Alumno> {
        return alumnos.toList()
    }

    fun save(alumno: Alumno): Alumno {
        require(alumno.nombre.isNotBlank()) { "El nombre del alumno no puede estar vacío" }
        require(alumno.calificacion in 0.0..10.0) { "La calificación debe estar entre 0 y 10" }
        alumnos.add(alumno)
        return alumno
    }

    fun delete(nombre: String) {
        require(nombre.isNotBlank()) { "El nombre del alumno no puede estar vacío" }
        alumnos.removeIf { it.nombre == nombre }
    }

    fun promedioCalificaciones(): Double {
        return alumnos.map { it.calificacion }.average()
    }

    fun clear() {
        alumnos.clear()
    }

    fun addAll(alumnos: List<Alumno>) {
        this.alumnos.addAll(alumnos)
    }
}