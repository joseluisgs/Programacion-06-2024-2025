package dev.joseluisgs.extensions

import kotlin.math.pow
import kotlin.math.round

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round(this * factor) / factor
}