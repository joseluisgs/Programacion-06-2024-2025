package dev.joseluisgs.extensions

import dev.joseluisgs.models.Estudiante


/**
 * Contar cuantos estudiantes hay que cumplen una condición
 * @param condition Condición a cumplir
 * @return Número de estudiantes que cumplen la condición
 */
fun Array<Estudiante?>.count(condition: (Estudiante) -> Boolean): Int {
    var count = 0
    for (estudiante in this) {
        if (estudiante != null && condition(estudiante)) {
            count++
        }
    }
    return count
}

/**
 * Buscar estudiantes que cumplan una condición
 * @param condition Condición a cumplir
 * @return Array de estudiantes que cumplen la condición
 */
fun Array<Estudiante?>.findBy(condition: (Estudiante) -> Boolean): Array<Estudiante> {
    // Contar cuantos hay que cumplen la condición
    // Crear un array con el tamaño correcto
    val result = Array(this.count(condition)) { Estudiante(nombre = "", edad = 0) }
    // Rellenar el array con los estudiantes que cumplen la condición
    var index = 0
    for (estudiante in this) {
        if (estudiante != null && condition(estudiante)) {
            result[index] = estudiante
            index++
        }
    }
    return result
}

/**
 * Realiza una acción sobre cada estudiante
 * @param action Acción a realizar
 * @return Unit
 */
fun Array<Estudiante?>.forEach(action: (Estudiante) -> Unit) {
    for (estudiante in this) {
        if (estudiante != null) {
            action(estudiante)
        }
    }
}

/**
 * Devuelve el índice de un estudiante que cumpla una condición
 * @param condition Condición a cumplir
 * @return Índice del estudiante que cumple la condición o -1 si no se encuentra
 */
fun Array<Estudiante?>.indexOf(condition: (Estudiante?) -> Boolean): Int {
    for (index in this.indices) {
        if (condition(this[index])) {
            return index
        }
    }
    return -1
}

/**
 * Devuelve el índice de un estudiante que cumpla una condición
 * @param condition Condición a cumplir
 * @return Índice del estudiante que cumple la condición o -1 si no se encuentra
 */
fun Array<Estudiante?>.average(condition: (Estudiante) -> Double): Double {
    var sum = 0.0
    var count = 0
    this.forEach { it ->
        sum += condition(it)
        count++
    }
    return if (count == 0) 0.0 else (sum / count)
}

/**
 * Devuelve el primer estudiante que cumpla una condición
 * @param condition Condición a cumplir
 * @return Estudiante que cumple la condición o null si no se encuentra
 */
fun Array<Estudiante>.firstOrNull(): Estudiante? {
    for (estudiante in this) {
        return estudiante

    }
    return null
}

fun Array<Estudiante?>.lastOrNull(): Estudiante? {
    for (index in this.indices.reversed()) {
        if (this[index] != null) {
            return this[index]
        }
    }
    return null
}

/**
 * Devuelve el primer estudiante que cumpla una condición
 * @param condition Condición a cumplir
 * @return Estudiante que cumple la condición o null si no se encuentra
 */
fun Array<Estudiante?>.maxBy(condition: (Estudiante) -> Double): Estudiante? {
    var max = Double.MIN_VALUE
    var maxEstudiante: Estudiante? = null
    for (estudiante in this) {
        if (estudiante != null && condition(estudiante) > max) {
            max = condition(estudiante)
            maxEstudiante = estudiante
        }
    }
    return maxEstudiante
}


/**
 * Devuelve el estudiante con la menor calificación que cumpla una condición
 * @param condition Condición a cumplir
 * @return Estudiante con la menor calificación que cumple la condición o null si no se encuentra
 */
fun Array<Estudiante?>.minBy(condition: (Estudiante) -> Double): Estudiante? {
    var min = Double.MAX_VALUE
    var minEstudiante: Estudiante? = null
    for (estudiante in this) {
        if (estudiante != null && condition(estudiante) < min) {
            min = condition(estudiante)
            minEstudiante = estudiante
        }
    }
    return minEstudiante
}

/**
 * Redimensiona el array de estudiantes
 * @param modo Modo de redimensión: Aumentar o Disminuir
 * @param maxEstudiantes Número máximo de estudiantes
 * @return Array de estudiantes redimensionado
 * @see ModoRedimension
 */
fun Array<Estudiante?>.redimensionar(modo: ModoRedimension, maxEstudiantes: Int): Array<Estudiante?> {
    val nuevoArray = arrayOfNulls<Estudiante>(maxEstudiantes)
    var index = 0
    for (estudiante in this) {
        if (estudiante != null || modo != ModoRedimension.DISMINUIR) {
            nuevoArray[index] = estudiante
            if (index < maxEstudiantes - 1) index++
        }
    }
    return nuevoArray
}

/**
 * Ordena un array de estudiantes por un campo
 * @param mode Modo de ordenamiento: Ascendente o Descendente
 * @param selector Campo por el que ordenar
 * @return Array de estudiantes ordenado
 * @see ModoOrdenamiento
 */

// NOTA, pronto lo haremos para que ordene en base a cualquier campo, pero por ahora solo ordena por calificación
fun Array<Estudiante?>.sortedBy(
    mode: ModoOrdenamiento = ModoOrdenamiento.DESCENDENTE,
    selector: (Estudiante) -> Double
): Array<Estudiante> {
    val result = this.findBy { true } // Copia del array
    // esto es bastante hard, no espero que lo hagas, si no que intentes entenderlo
    val compare: (Double, Double) -> Boolean =
        if (mode == ModoOrdenamiento.ASCENDENTE) { a, b -> a > b } else { a, b -> a < b } // Función de comparación, ahora entiende esto todo funcional

    for (i in result.indices) {
        for (j in 0..<result.size - 1 - i) {
            if (compare(selector(result[j]), selector(result[j + 1]))) {
                result[j] = result[j + 1].also { result[j + 1] = result[j] }
            }
        }
    }
    return result
}

/**
 * Enumerado de modos de redimensión: Aumentar o Disminuir
 */
enum class ModoRedimension {
    AUMENTAR, DISMINUIR
}

/**
 * Enumerado de modos de ordenamiento: Ascendente o Descendente
 */
enum class ModoOrdenamiento {
    ASCENDENTE, DESCENDENTE
}