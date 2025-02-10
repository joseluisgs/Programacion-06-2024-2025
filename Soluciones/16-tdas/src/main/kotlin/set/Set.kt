package dev.joseluisgs.set


import dev.joseluisgs.list.InmutableList
import dev.joseluisgs.list.MutableList

/**
 * Interfaz de lista
 */
interface Set<T> : MutableList<T>, InmutableList<T>