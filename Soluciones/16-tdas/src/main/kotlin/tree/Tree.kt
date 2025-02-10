package dev.joseluisgs.tree

import dev.joseluisgs.list.LinkedList


interface Tree<T> {
    operator fun contains(value: T): Boolean
    fun add(value: T)
    fun update(oldValue: T, newValue: T)
    fun delete(value: T)
    fun hight(): Int
    fun weight(): Int
    fun level(value: T): Int?
    fun leafNodes(): Int
    fun preOrder(): LinkedList<T>
    fun inOrder(): LinkedList<T>
    fun postOrder(): LinkedList<T>
    fun printTree()
}
