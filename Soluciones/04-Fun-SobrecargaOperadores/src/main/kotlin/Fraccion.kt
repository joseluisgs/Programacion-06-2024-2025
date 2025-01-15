package dev.joseluisgs

data class Fraccion(val numerator: Int, val denominator: Int) {
    init {
        require(denominator != 0) { "Denominator can't be zero" }
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }
}

operator fun Fraccion.plus(other: Fraccion): Fraccion {
    val newNumerator = numerator * other.denominator + other.numerator * denominator
    val newDenominator = denominator * other.denominator
    return Fraccion(newNumerator, newDenominator)
}

operator fun Fraccion.minus(other: Fraccion): Fraccion {
    val newNumerator = numerator * other.denominator - other.numerator * denominator
    val newDenominator = denominator * other.denominator
    return Fraccion(newNumerator, newDenominator)
}

operator fun Fraccion.times(other: Fraccion): Fraccion {
    val newNumerator = numerator * other.numerator
    val newDenominator = denominator * other.denominator
    return Fraccion(newNumerator, newDenominator)
}

operator fun Fraccion.div(other: Fraccion): Fraccion {
    val newNumerator = numerator * other.denominator
    val newDenominator = denominator * other.numerator
    return Fraccion(newNumerator, newDenominator)
}

operator fun Fraccion.unaryMinus(): Fraccion {
    return Fraccion(-numerator, denominator)
}

operator fun Fraccion.compareTo(other: Fraccion): Int {
    val res1 = this.numerator.toDouble() / this.denominator
    val res2 = other.numerator.toDouble() / other.denominator
    return when {
        res1 > res2 -> 1
        res1 < res2 -> -1
        else -> 0
    }
}