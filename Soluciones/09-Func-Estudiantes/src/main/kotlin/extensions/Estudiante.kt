package dev.joseluisgs.extensions

import dev.joseluisgs.models.Estudiante

// SafeBuilder para Estudainte

/**
 * SafeBuilder para Estudiante
 * @param actions Acciones a realizar
 * @return Estudiante
 */
fun buildEstudiante(actions: Estudiante.() -> Unit): Estudiante {
    val estudiante = Estudiante("", 0.0)
    estudiante.actions()
    return estudiante
}

/**
 * Genera un estudiante aleatorio
 * @return Estudiante
 */
fun randomEstudiante(): Estudiante {
    val nombres = arrayOf("Pepe", "Juan", "Luis", "Ana", "Maria", "Lola", "Manuel", "Antonio", "Jose", "Pedro")
    return Estudiante(
        nombre = nombres.random(),
        calificacion = (0..10).random().toDouble().roundTo(2)
    )
}

/**
 * Funciones de extensión para Array de Estudiantes. Recorremos el array y ejecutamos la acción pasada por parámetro
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.forEach(action: (Estudiante) -> Unit) {
    for (element in this) action(element)
}

/**
 * Funciones de extensión para Array de Estudiantes. Mapeamos el array y ejecutamos la acción pasada por parámetro
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.map(transform: (Estudiante) -> Estudiante): Array<Estudiante> {
    val result = Array(this.size) { Estudiante("", 0.0) }
    for (index in this.indices) {
        result[index] = transform(this[index])
    }
    return result
}

/**
 * Funciones de extensión para Array de Estudiantes. Filtramos el array y ejecutamos la acción pasada por parámetro
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.filter(predicate: (Estudiante) -> Boolean): Array<Estudiante> {
    // Contamos los elementos que cumplen el predicado
    /*var count = 0
    for (element in this) {
        if (predicate(element)) count++
    }

    // Creamos un nuevo array con el tamaño correcto
    val finalResult: Array<Estudiante> = Array(count) { Estudiante("", 0.0) }*/

    val finalResult = Array(count(predicate)) { Estudiante("", 0.0) }

    var finalIndex = 0
    for (element in this) {
        if (predicate(element)) {
            finalResult[finalIndex] = element
            finalIndex++
        }
    }
    return finalResult
}


/**
 * Funciones de extensión para Array de Estudiantes. Contamos los elementos que cumplen el predicado
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.count(predicate: (Estudiante) -> Boolean): Int {
    var count = 0
    for (element in this) {
        if (predicate(element)) count++
    }
    return count
}

/**
 * Funciones de extensión para Array de Estudiantes. Buscamos un elemento que cumple el predicado
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.find(condition: (Estudiante) -> Boolean): Estudiante? {
    for (element in this) {
        if (condition(element)) return element
    }
    return null
}

/**
 * Funciones de extensión para Array de Estudiantes. Buscamos el primer elemento que cumple el predicado
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.first(predicate: (Estudiante) -> Boolean): Estudiante? {
    for (element in this) {
        if (predicate(element)) return element
    }
    return null
}

/**
 * Funciones de extensión para Array de Estudiantes. Buscamos el último elemento que cumple el predicado
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.last(predicate: (Estudiante) -> Boolean): Estudiante? {
    //var last: Estudiante? = null
    /*for (element in this) {
        if (predicate(element)) last = element
    }
    return last
    */

    // Recorremos hacia atrás
    for (i in this.indices.reversed()) {
        if (predicate(this[i])) return this[i]
    }

    // Si no lo encontramos, devolvemos null
    return null

}

/**
 * Funciones de extensión para Array de Estudiantes. Buscamos el máximo de un selector
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.maxBy(selector: (Estudiante) -> Double): Estudiante? {
    if (this.isEmpty()) return null
    var maxElement = this[0]
    var maxValue = selector(maxElement)
    for (element in this) {
        val value = selector(element)
        if (value > maxValue) {
            maxElement = element
            maxValue = value
        }
    }
    return maxElement
}

/**
 * Funciones de extensión para Array de Estudiantes. Buscamos el mínimo de un selector
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.minBy(selector: (Estudiante) -> Double): Estudiante? {
    if (this.isEmpty()) return null
    var minElement = this[0]
    var minValue = selector(minElement)
    for (element in this) {
        val value = selector(element)
        if (value < minValue) {
            minElement = element
            minValue = value
        }
    }
    return minElement
}

/**
 * Funciones de extensión para Array de Estudiantes. Sumamos los valores de un selector
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.sumBy(selector: (Estudiante) -> Double): Double {
    var sum = 0.0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * Funciones de extensión para Array de Estudiantes. Media de los valores de un selector
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.averageBy(selector: (Estudiante) -> Double): Double {
    return sumBy(selector) / this.size
}

/**
 * Funciones de extensión para Array de Estudiantes. Ordenamos el array por un selector
 * @receiver Array<Estudiante>
 *
 */
fun Array<Estudiante>.sortedBy(mode: Mode = Mode.DESC, selector: (Estudiante) -> Double): Array<Estudiante> {
    val result =
        this.copyOf() // Lo copiamos para no modificar el original (esta es de kotlin pero deberiamos hacerlo a mano)
    val compare: (Double, Double) -> Boolean =
        if (mode == Mode.ASC) { a, b -> a > b } else { a, b -> a < b } // Función de comparación, ahora entiende esto todo funcional
    // Metodo de ordenación burbuja
    for (i in result.indices) {
        for (j in 0..<result.size - 1 - i) {
            if (compare(selector(result[j]), selector(result[j + 1]))) {
                //val temp = result[j]
                //result[j] = result[j + 1]
                //result[j + 1] = temp

                // Con also{}
                result[j] = result[j + 1].also { result[j + 1] = result[j] }
            }
        }
    }
    return result
}

/**
 * Funciones de extensión para Array de Estudiantes. Añadimos un elemento al array
 * @receiver Array<Estudiante>
 *
 */
operator fun Array<Estudiante>.plus(other: Estudiante): Array<Estudiante> {
    val result = Array(this.size + 1) { Estudiante("", 0.0) }
    for (index in this.indices) {
        result[index] = this[index]
    }
    result[this.size] = other
    return result
}

/**
 * Funciones de extensión para Array de Estudiantes. Eliminamos un elemento al array
 * @receiver Array<Estudiante>
 *
 */
operator fun Array<Estudiante>.minus(other: Estudiante): Array<Estudiante> {
    // Buscamos si existe con algunas de nuestras funcones o con contains que hemos hecho (find, o first, count o contains)
    if (!this.contains(other)) return this

    val result = Array(this.size - 1) { Estudiante("", 0.0) }
    var index = 0
    for (element in this) {
        if (element != other) {
            result[index] = element
            index++
        }
    }
    return result
}

/**
 * Funciones de extensión para Array de Estudiantes. Comprobamos si un elemento está en el array
 * @receiver Array<Estudiante>
 *
 */
operator fun Array<Estudiante>.contains(e: Estudiante): Boolean {
    for (element in this) {
        if (element == e) return true
    }
    return false
}


/**
 * Modo de ordenación
 */
enum class Mode {
    ASC, DESC
}