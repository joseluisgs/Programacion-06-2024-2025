package dev.joseluisgs.commons


/**
 * Nodo de un árbol binario
 * @param T Tipo de dato del nodo
 * @property value Valor del nodo
 * @property left Hijo izquierdo
 * @property right Hijo derecho
 * @constructor Crea un nodo con un valor y posibles hijos
 */
data class BinaryNode<T>(
    var value: T,
    var left: BinaryNode<T>? = null,
    var right: BinaryNode<T>? = null
)