package dev.joseluisgs.extensions

import kotlin.math.round

fun Double.roundTo(numberOfDecimals: Int): Double {
    require(numberOfDecimals >= 0)
    var multiplier = 1.0
    repeat(numberOfDecimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}