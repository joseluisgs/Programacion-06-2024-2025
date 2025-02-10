package dev.joseluisgs.locale

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.pow

private val myLocale = Locale("es", "ES")

fun LocalDate.toLocalDate(): String {
    return this.format(
        DateTimeFormatter
            .ofLocalizedDate(FormatStyle.SHORT).withLocale(myLocale)
    )
}

fun Double.toLocalMoney(): String {
    return NumberFormat.getCurrencyInstance(myLocale).format(this)
}

fun Double.toLocalDecimal(): String {
    return NumberFormat.getInstance(myLocale).format(this)
}

fun Int.toLocalInteger(): String {
    return NumberFormat.getInstance(myLocale).format(this)
}

fun Double.roundTo(decimals: Int): Double {
    return Math.round(this * 10.0.pow(decimals)) / 10.0.pow(decimals)
}