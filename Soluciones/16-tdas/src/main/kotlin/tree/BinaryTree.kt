package dev.joseluisgs.tree

import dev.joseluisgs.commons.BinaryNode
import dev.joseluisgs.list.LinkedList

/**
 * Árbol binario con operaciones comunes
 * @param T Tipo de dato del árbol
 */
class BinaryTree<T : Comparable<T>> : Tree<T> {
    private var root: BinaryNode<T>? = null

    /**
     * Comprueba si un valor está en el árbol
     * @param value Valor a comprobar
     * @return Si el valor está en el árbol
     */
    override operator fun contains(value: T): Boolean {
        return containsRec(root, value)
    }

    /**
     * Comprueba si un valor está en el árbol de forma recursiva
     * @param node Nodo actual
     * @param value Valor a comprobar
     * @return Si el valor está en el árbol
     */
    private fun containsRec(node: BinaryNode<T>?, value: T): Boolean {
        if (node == null) return false
        return when {
            value == node.value -> true
            value < node.value -> containsRec(node.left, value)
            else -> containsRec(node.right, value)
        }
    }

    /**
     * Insertar un valor en el árbol
     * @param value Valor a insertar
     */
    override fun add(value: T) {
        root = addRec(root, value)
    }

    /**
     * Insertar un valor en el árbol de forma recursiva
     * @param node Nodo actual
     * @param value Valor a insertar
     */
    private fun addRec(node: BinaryNode<T>?, value: T): BinaryNode<T> {
        if (node == null) return BinaryNode(value)
        if (value < node.value) { // Usamos compareTo para comparar valores
            node.left = addRec(node.left, value)
        } else {
            node.right = addRec(node.right, value)
        }
        return node
    }

    /**
     * actualizar un valor en el árbol
     * @param oldValue Valor a actualizar
     * @param newValue Nuevo valor
     */
    override fun update(oldValue: T, newValue: T) {
        delete(oldValue)
        add(newValue)
    }

    /**
     * Buscar un valor en el árbol
     * @param value Valor a borrar
     */
    override fun delete(value: T) {
        root = deleteRec(root, value)
    }

    /**
     * Borrar un valor en el árbol de forma recursiva
     * @param node Nodo actual
     * @param value Valor a borrar
     * @return Nodo actualizado
     */
    private fun deleteRec(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
        if (node == null) return null
        when {
            value.hashCode() < node.value.hashCode() -> node.left = deleteRec(node.left, value)
            value.hashCode() > node.value.hashCode() -> node.right = deleteRec(node.right, value)
            else -> {
                if (node.left == null) return node.right
                if (node.right == null) return node.left
                node.value = findMin(node.right).value
                node.right = deleteRec(node.right, node.value)
            }
        }
        return node
    }

    /**
     * Buscar el valor mínimo en un subárbol
     * @param node Nodo actual
     * @return Nodo con el valor mínimo
     */
    private fun findMin(node: BinaryNode<T>?): BinaryNode<T> {
        var current = node
        while (current?.left != null) {
            current = current.left
        }
        return current!!
    }

    /**
     * Devuelve si un valor está en el árbol
     * @return altura del árbol
     */
    override fun hight(): Int {
        return hightRec(root)
    }

    /**
     * Devuelve la altura de un árbol de forma recursiva
     * @param node Nodo actual
     * @return altura del árbol
     */
    private fun hightRec(node: BinaryNode<T>?): Int {
        if (node == null) return 0
        return 1 + maxOf(hightRec(node.left), hightRec(node.right))
    }


    /**
     * Devuleve el peso del árbol (número de nodos)
     * @return peso del árbol
     */
    override fun weight(): Int {
        return weightRec(root)
    }

    /**
     * Devuelve el peso de un árbol de forma recursiva
     * @param node Nodo actual
     * @return peso del árbol
     */
    private fun weightRec(node: BinaryNode<T>?): Int {
        if (node == null) return 0
        return 1 + weightRec(node.left) + weightRec(node.right)
    }

    /**
     * Devuelve el nivel de un valor en el árbol
     * @param value Valor a buscar
     * @return Nivel del valor en el árbol
     */
    override fun level(value: T): Int? {
        return levelRec(root, value, 0)
    }

    /**
     * Devuelve el nivel de un valor en el árbol de forma recursiva
     * @param node Nodo actual
     * @param value Valor a buscar
     * @param currentLevel Nivel actual
     * @return Nivel del valor en el árbol
     */
    private fun levelRec(node: BinaryNode<T>?, value: T, currentLevel: Int): Int? {
        if (node == null) return null // El valor no está en el árbol
        return when {
            value == node.value -> currentLevel
            value < node.value -> levelRec(node.left, value, currentLevel + 1)
            else -> levelRec(node.right, value, currentLevel + 1)
        }
    }

    /**
     * Devuelve el número de nodos hoja
     * @return Número de nodos hoja
     */
    override fun leafNodes(): Int {
        return leafNodesRec(root)
    }

    /**
     * Devuelve el número de nodos hoja de un árbol de forma recursiva
     * @param node Nodo actual
     * @return Número de nodos hoja
     * */
    private fun leafNodesRec(node: BinaryNode<T>?): Int {
        if (node == null) return 0
        if (node.left == null && node.right == null) return 1 // Es un nodo hoja
        return leafNodesRec(node.left) + leafNodesRec(node.right)
    }


    /**
     * Recorrido en preorden: Raíz, izquierda, derecha
     * @return Lista con el recorrido en preorden
     */
    override fun preOrder(): LinkedList<T> {
        val resultado = LinkedList<T>()
        preOrderRec(root, resultado)
        return resultado
    }

    /**
     * Recorrido en preorden de forma recursiva
     * @param node Nodo actual
     * @param resultado Lista con el recorrido
     */
    private fun preOrderRec(node: BinaryNode<T>?, resultado: LinkedList<T>) {
        if (node != null) {
            resultado.add(node.value)
            preOrderRec(node.left, resultado)
            preOrderRec(node.right, resultado)
        }
    }

    /**
     * Recorrido en inorden: izquierda, raíz, derecha
     * @return Lista con el recorrido en inorden
     */
    override fun inOrder(): LinkedList<T> {
        val resultado = LinkedList<T>()
        inOrderRec(root, resultado)
        return resultado
    }

    private fun inOrderRec(node: BinaryNode<T>?, resultado: LinkedList<T>) {
        if (node != null) {
            inOrderRec(node.left, resultado)
            resultado.add(node.value)
            inOrderRec(node.right, resultado)
        }
    }

    /**
     * Recorrido en postorden: izquierda, derecha, raíz
     * @return Lista con el recorrido en postorden
     */
    override fun postOrder(): LinkedList<T> {
        val resultado = LinkedList<T>()
        postOrderRec(root, resultado)
        return resultado
    }

    private fun postOrderRec(node: BinaryNode<T>?, resultado: LinkedList<T>) {
        if (node != null) {
            postOrderRec(node.left, resultado)
            postOrderRec(node.right, resultado)
            resultado.add(node.value)
        }
    }

    /**
     * Imprime el árbol
     */
    override fun printTree() {
        if (root == null) {
            println("Árbol vacío")
            return
        }
        printTreeRec(root, "", true)
    }

    /**
     * Imprime el árbol en consola
     */
    private fun printTreeRec(node: BinaryNode<T>?, prefix: String, isTail: Boolean) {
        if (node != null) {
            println(prefix + (if (isTail) "└── " else "├── ") + node.value)
            printTreeRec(node.left, prefix + if (isTail) "    " else "│   ", node.right == null)
            printTreeRec(node.right, prefix + if (isTail) "    " else "│   ", true)
        }
    }

    override fun toString(): String {
        if (root == null) {
            return "Empty tree"
        }
        val sb = StringBuilder()
        toStringRec(root, "", true, sb)
        return sb.toString()
    }

    private fun toStringRec(node: BinaryNode<T>?, prefix: String, isTail: Boolean, sb: StringBuilder) {
        if (node != null) {
            sb.append(prefix).append(if (isTail) "└── " else "├── ").append(node.value).append("\n")
            toStringRec(node.left, prefix + if (isTail) "    " else "│   ", node.right == null, sb)
            toStringRec(node.right, prefix + if (isTail) "    " else "│   ", true, sb)
        }
    }


}
