package dev.joseluisgs.models

class Triple<T, U, V>(var first: T, var second: U, var third: V) {
    override fun toString(): String {
        return "Triple(first=$first, second=$second, third=$third)"
    }
}