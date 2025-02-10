package dev.joseluisgs

import dev.joseluisgs.extensions.*

fun main() {
    println("Generando estudiante con SafeBuilder:")
    val e1 = buildEstudiante {
        nombre = "Pepe"
        calificacion = 5.0
    }
    println(e1)

    println("Generando estudiante aleatorio con SafeBuilder:")
    val e2 = buildEstudiante {
        nombre = "Juan"
        calificacion = 7.0
    }
    println(e2)

    println("Generando estudiante aleatorio:")
    val e3 = randomEstudiante()
    println(e3)

    val estudiantes = arrayOf(e1, e2, e3)

    println("Todos los estudiantes:")
    estudiantes.forEach { estudiante -> println(estudiante) }

    println("Estudiantes aprobados:")
    val estudiantesAprobados = estudiantes.filter { it.calificacion >= 5.0 }
    estudiantesAprobados.forEach { println(it) }

    println("Estudiantes suspensos:")
    val estudiantesSuspensos = estudiantes.filter { it.calificacion < 5.0 }
    estudiantesSuspensos.forEach { println(it) }

    val estduantesConNotables = estudiantes.filter { it.calificacion >= 7.0 && it.calificacion < 9.0 }
    println("Estudiantes con notables:")
    estduantesConNotables.forEach { println(it) }

    println("Estudiantes con nombre Pepe:")
    val estudiantesPepe = estudiantes.filter { it.nombre == "Pepe" }
    estudiantesPepe.forEach { println(it) }

    val estudiantesBusqueda = estudiantes.filter { it.nombre.contains("pe", ignoreCase = true) }
    estudiantesBusqueda.forEach { println(it) }

    // Ponemos sus nombres en mayúsculas
    println("Estudiantes con nombre en mayúsculas:")
    val estudiantesMayusculas = estudiantes.map { it.copy(nombre = it.nombre.uppercase()) }
    estudiantesMayusculas.forEach { println(it) }

    // Numero de estudiantes aprobados
    val numeroEstudiantesAprobados = estudiantes.count { it.calificacion >= 5.0 }
    println("Número de estudiantes aprobados: $numeroEstudiantesAprobados")

    // Primer estudiante aprobado
    val primerEstudianteAprobado = estudiantes.first { it.calificacion >= 5.0 }
    println("Primer estudiante aprobado: $primerEstudianteAprobado")

    // Ultimo estudiante aprobado
    val ultimoEstudianteAprobado = estudiantes.last { it.calificacion >= 5.0 }
    println("Ultimo estudiante aprobado: $ultimoEstudianteAprobado")

    // Estudiante con nombre llamado Pepe
    val estudiantePepe = estudiantes.first { it.nombre == "Pepe" }
    println("Estudiante con nombre Pepe: $estudiantePepe")

    // Nota maxima
    val notaMaxima = estudiantes.maxBy { it.calificacion }
    println("Nota máxima: $notaMaxima")

    // Nota minima
    val notaMinima = estudiantes.minBy { it.calificacion }
    println("Nota mínima: $notaMinima")

    // Suma de todas las notas
    val sumaNotas = estudiantes.sumBy { it.calificacion }
    println("Suma de todas las notas: $sumaNotas")

    // Media de todas las notas
    val mediaNotas = estudiantes.averageBy { it.calificacion }
    println("Media de todas las notas: ${mediaNotas.roundTo(2)}")

    // Ordenamos por nota
    val estudiantesOrdenados = estudiantes.sortedBy(Mode.ASC) { it.calificacion }
    println("Estudiantes ordenados por nota descendentemente (Se puede indicar ascendentemente:")
    estudiantesOrdenados.forEach { println(it) }

    println("Cremos un nuevo estudiante e5 y lo añadimos al array:")
    val e5 = buildEstudiante {
        nombre = "Otro"
        calificacion = 5.0
    }
    val nuevoEstudiantes = estudiantes + e5
    println("Nuevo array de estudiantes:")
    nuevoEstudiantes.forEach { println(it) }

    // Eliminamos e5 del array
    val nuevoEstudiantesSinE5 = nuevoEstudiantes - e5
    println("Nuevo array de estudiantes sin e5:")
    nuevoEstudiantesSinE5.forEach { println(it) }

    // Buscamos si e1 está en el array
    val estaE1 = e1 in nuevoEstudiantesSinE5
    println("¿Está e1: ${e1.nombre} en el array? $estaE1")

    // Buscamos si hay otro estudiante con nombre Vaca
    val estudianteVaca = nuevoEstudiantesSinE5.find { it.nombre == "Vaca" }
    println("Estudiante con nombre Vaca: $estudianteVaca")

    // Buscamos si hay otro estudiante con nota igual a 7.0
    val estudianteNota7 = nuevoEstudiantesSinE5.find { it.calificacion == 7.0 }
    println("Estudiante con nota 7.0: $estudianteNota7")


}
