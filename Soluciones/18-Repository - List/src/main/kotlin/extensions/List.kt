package dev.joseluisgs.extensions


/**
 * Contar cuantos items hay que cumplen una condición
 * @param predicate Condición a cumplir
 * @return Número de items que cumplen la condición
 */
inline fun <T> List<T>.countBy(predicate: (T) -> Boolean = { true }): Int {
    var count = 0
    for (element in this) {
        if (predicate(element)) {
            count++
        }
    }
    return count
}

/**
 * Buscar item que cumplan una condición
 * @param predicate Condición a cumplir
 * @return Array de item que cumplen la condición
 */
inline fun <reified T> List<T>.filterBy(predicate: (T) -> Boolean = { true }): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}

/**
 * Realiza una acción sobre cada item
 * @param action Acción a realizar
 * @return Unit
 */
fun <T> List<T>.forEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

/**
 * Devuelve el índice de un item que cumpla una condición
 * @param condition Condición a cumplir
 * @return Índice del item que cumple la condición o -1 si no se encuentra
 */
fun <T> List<T>.indexOf(condition: (T) -> Boolean = { true }): Int {
    for (index in this.indices) {
        if (condition(this[index])) {
            return index
        }
    }
    return -1
}

/**
 * Devuelve el índice de un item que cumpla una condición
 * @param predicate Condición a cumplir
 * @return Índice del item que cumple la condición o -1 si no se encuentra
 */
fun <T> List<T>.averageBy(predicate: (T) -> Boolean = { true }): Double {
    var count = 0
    var total = 0.0
    for (element in this) {
        if (predicate(element)) {
            total += (element as? Number)?.toDouble() ?: 0.0
            count++
        }
    }
    return if (count == 0) 0.0 else total / count
}

/**
 * Suma los valores de un campo de los items que cumplan una condición
 * @param predicate Condición a cumplir
 *
 */
fun <T> List<T>.sumBy(predicate: (T) -> Boolean = { true }): Double {
    var total = 0.0
    for (element in this) {
        if (predicate(element)) {
            total += (element as? Number)?.toDouble() ?: 0.0
        }
    }
    return total
}

/**
 * Devuelve el primer item que cumpla una condición
 * @param predicate Condición a cumplir
 * @return item que cumple la condición o null si no se encuentra
 */
fun <T> List<T>.firstOrNull(predicate: (T) -> Boolean = { true }): T? {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    return null
}

/**
 * Devuelve el último item que cumpla una condición
 * @param predicate Condición a cumplir
 * @return item que cumple la condición o null si no se encuentra
 */
fun <T> List<T>.lastOrNull(predicate: (T) -> Boolean = { true }): T? {
    var lastMatch: T? = null
    for (element in this) {
        if (predicate(element)) {
            lastMatch = element
        }
    }
    return lastMatch
}

/**
 * Devuelve el máximo item que cumpla una condición
 * @param selector Campo por el que buscar el máximo
 * @param predicate Condición a cumplir
 * @return item que cumple el máximo que cumple la condición o null si no se encuentra
 */
fun <T> List<T>.maxByOrNull(selector: (T) -> Double, predicate: (T) -> Boolean = { true }): T? {
    var maxElement: T? = null
    var maxValue: Double? = null
    for (element in this) {
        if (predicate(element)) {
            val value = selector(element)
            if (maxValue == null || value > maxValue) {
                maxValue = value
                maxElement = element
            }
        }
    }
    return maxElement
}

/**
 * Devuelve el item con la menor valor que cumpla la condición
 * @param selector Campo por el que buscar el mínimo
 * @param predicate Condición a cumplir
 * @return Item con la menor valor que cumple la condición o null si no se encuentra
 */
fun <T> List<T>.minByOrNull(selector: (T) -> Double, predicate: (T) -> Boolean = { true }): T? {
    var minElement: T? = null
    var minValue: Double? = null
    for (element in this) {
        if (predicate(element)) {
            val value = selector(element)
            if (minValue == null || value < minValue) {
                minValue = value
                minElement = element
            }
        }
    }
    return minElement
}


/**
 * Ordena un array de items por un campo
 * @param mode Modo de ordenamiento: Ascendente o Descendente
 * @param selector Campo por el que ordenar
 * @return Array de items ordenado
 * @see ModoOrdenamiento
 */

// NOTA, pronto lo haremos para que ordene en base a cualquier campo, pero por ahora solo ordena por calificación
inline fun <reified T> List<T>.sortedBy(
    mode: ModoOrdenamiento = ModoOrdenamiento.DESCENDENTE,
    selector: (T) -> Double
): List<T> {
    val result = this.filterBy { true }.toMutableList()
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
    return result.toList()
}

inline fun <reified T> List<T>.toArray(): Array<T> {
    return Array(this.size) { this[it] }
}

inline fun <reified T> Array<T>.toList(): List<T> {
    val res = mutableListOf<T>()
    for (i in this.indices) {
        res.add(this[i])
    }
    return res
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