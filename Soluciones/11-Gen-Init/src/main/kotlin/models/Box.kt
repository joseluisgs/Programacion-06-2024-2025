package dev.joseluisgs.models

class Box<T>(var item: T) {

    fun get(): T {
        return item
    }

    fun put(item: T) {
        this.item = item
    }

    override fun toString(): String {
        return "Box(item=$item)"
    }
}