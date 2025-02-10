package dev.joseluisgs.models

class Pair<T, U>(var first: T, var second: U) {
    override fun toString(): String {
        return "Pair(first=$first, second=$second)"
    }
}