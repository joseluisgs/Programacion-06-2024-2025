package dev.joseluisgs.factories

import dev.joseluisgs.models.Estudiante

class EstudianteFactory {

    companion object {
        fun estudianteRandom(): Estudiante {
            val nombre = arrayOf(
                "Jose",
                "Luis",
                "Pepe",
                "Manolo",
                "Antonio",
                "Maria",
                "Lola",
                "Ana",
                "Paco",
                "Juan",
                "Julia",
                "Eva",
                "Alica"
            )
            val edad = (18..50).random()
            val calificacion = (0..10).random().toDouble()
            return Estudiante(nombre = nombre.random(), edad = edad, calificacion = calificacion)
        }

    }
}