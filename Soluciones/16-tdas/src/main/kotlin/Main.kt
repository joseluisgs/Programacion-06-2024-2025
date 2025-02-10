package dev.joseluisgs

import dev.joseluisgs.extensions.*
import dev.joseluisgs.list.linkedListOf
import dev.joseluisgs.list.readOnlyListOf
import dev.joseluisgs.map.Map
import dev.joseluisgs.map.mapOf
import dev.joseluisgs.queue.Cola
import dev.joseluisgs.set.linkedSetOf
import dev.joseluisgs.stack.Pila
import dev.joseluisgs.tree.BinaryTree

fun main() {
    println("Probando LinkedList")
    val lista = linkedListOf<Int>(1, 2, 3, 4, 5)
    println(lista)
    // Vamos a probar las operaciones de la lista
    lista.add(6)
    println(lista)
    lista.add(0, 0)
    println(lista)
    lista[3] = 10  // lista.set(3, 10)
    println(lista)
    println("Elemento en la posición 3: ${lista[3]}") // lista.get(3)
    println("Tamaño de la lista: ${lista.size}")
    println("¿Está vacía la lista?: ${lista.isEmpty()}")
    println("¿Contiene el 10?: ${lista.contains(10)}")
    println("¿Contiene el 20?: ${20 in lista}")
    lista.remove(10)
    println(lista)
    lista.removeAt(0)
    println(lista)
    lista[0] = 100
    println(lista)
    lista.clear()
    println(lista)
    println("¿Está vacía la lista ahora?: ${lista.isEmpty()}")
    println("¿Contiene el 10 ahora?: ${lista.contains(10)}")
    println("Tamaño de la lista ahora: ${lista.size}")


    println()
    println("Probando ReadList")
    val readList = readOnlyListOf("Hola", "Mundo")
    println(readList)
    println("Elemento en la posición 1: ${readList[1]}")
    println("Tamaño de la lista: ${readList.size}")
    println("¿Está vacía la lista?: ${readList.isEmpty()}")


    println()
    println("Probando Pila")
    val pila = Pila<Int>()
    pila.push(1)
    pila.push(2)
    pila.push(3)
    println(pila)
    println("Elemento en la cima: ${pila.peek()}")
    println("Elemento eliminado: ${pila.pop()}")
    println(pila)
    println("Elemento eliminado: ${pila.pop()}")
    println(pila)
    println("Tamaño de la pila: ${pila.size}")
    println("¿Está vacía la pila?: ${pila.isEmpty()}")

    println()
    println("Probando Cola")
    val cola = Cola<String>()
    cola.enqueue("Hola")
    cola.enqueue("Mundo")
    println(cola)
    println("Elemento en el frente: ${cola.peek()}")
    println("Elemento eliminado: ${cola.dequeue()}")
    println(cola)
    println("Elemento eliminado: ${cola.dequeue()}")
    println(cola)
    println("Tamaño de la cola: ${cola.size}")
    println("¿Está vacía la cola?: ${cola.isEmpty()}")

    println()
    println("Probando Conjunto")
    val conjunto = linkedSetOf(1, 2, 3, 4, 5)
    println(conjunto)
    conjunto.add(6)
    println(conjunto)
    conjunto.add(6)
    conjunto[0] = 5
    println(conjunto)
    println("¿Está vacío el conjunto ahora?: ${conjunto.isEmpty()}")
    println("Tamaño del conjunto ahora: ${conjunto.size}")
    println("Elemento 5 está en el conjunto?: ${5 in conjunto}")
    println("Elemento 10 está en el conjunto?: ${10 in conjunto}")
    conjunto.remove(5)
    println(conjunto)
    conjunto.removeAt(0)
    println(conjunto)
    conjunto[0] = 100
    println(conjunto)
    conjunto.clear()
    println(conjunto)
    println("¿Está vacío el conjunto ahora?: ${conjunto.isEmpty()}")
    println("Tamaño del conjunto ahora: ${conjunto.size}")

    println()
    println("Probando operaciones con colecciones")
    val lista1 = linkedListOf(1, 2, 3, 4, 5)
    println("imprimimos la lista")
    lista1.forEach { println(it) }
    println("imprimimos la lista en posiciones")
    lista1.forEachIndexed { index, value -> println("Posición $index: $value") }
    println("Filtramos los pares")
    lista1.filter { it % 2 == 0 }.forEach { println(it) }
    println("Multiplicamos por 2")
    lista1.map { it * 2 }.forEach { println(it) }
    println("Buscamos el 3")
    println(lista1.findOrNull { it == 3 })
    println("Contamos los pares")
    println(lista1.count { it % 2 == 0 })
    println("Primer par")
    println(lista1.firstOrNull { it % 2 == 0 })
    println("Último par")
    println(lista1.lastOrNull { it % 2 == 0 })
    println("Suma de todos los elementos")
    println(lista1.sumBy { it })
    println("Suma de todos los elementos pares")
    println(lista1.sumBy { if (it % 2 == 0) it else 0 })
    println("Media de todos los elementos")
    println(lista1.averageBy { it })
    println("Media de todos los elementos pares")
    println(lista1.averageBy { if (it % 2 == 0) it else 0 })
    println("Máximo de todos los elementos")
    println(lista1.maxOrNull { it })
    println("Mínimo de todos los elementos pares")
    println(lista1.maxOrNull { it % 2 == 0 })
    println("Mínimo de todos los elementos")
    println(lista1.minOrNull { it })
    println("Mínimo de todos los elementos pares")
    println(lista1.minOrNull { it % 2 == 0 })
    println("Suma de todos los elementos")
    println(lista1.reduce { acc, value -> acc + value })
    println("Suma de todos los elementos con valor inicial")
    println(lista1.fold(0) { acc, value -> acc + value })
    println("Hay algún par")
    println(lista1.any { it % 2 == 0 })
    println("Todos son pares")
    println(lista1.all { it % 2 == 0 })
    println("Ninguno es par")
    println(lista1.none { it % 2 == 0 })
    println("Particionamos en tamaño 2")
    lista1.chunked(2).forEach { println(it) }
    println("Tomamos mietras sean impares")
    lista1.takeWhile { it % 2 != 0 }.forEach { println(it) }
    println("Tomamos los 2 primeros")
    lista1.take(2).forEach { println(it) }
    println("Descartamos los 2 primeros")
    lista1.drop(2).forEach { println(it) }

    println()
    println("Probando operaciones con mapas")
    val map = Map<Int, String>()
    map.add(1, "uno")
    map.add(2, "dos")
    map.add(3, "tres")
    println(map)
    map.add(1, "cuatro")
    println(map)
    println("Valor de la clave 2: ${map.get(2)}")
    println("Existe la clave 2?: ${2 in map}")
    map.remove(2)
    println(map)
    println("Existe la clave 2?: ${2 in map}")

    val map2 = mapOf("1" to "uno", "2" to "dos", "2" to "tres")
    println(map2)
    println("Valor de la clave 2: ${map2.get("2")}")
    println("Existe la clave 2?: ${"2" in map2}")


    println()
    println("Probando arboles")
    val arbol = BinaryTree<Int>()
    arbol.add(10)
    arbol.add(5)
    arbol.add(15)
    arbol.add(3)

    println("Inorden: ${arbol.inOrder()}")
    println("Preorden: ${arbol.preOrder()}")
    println("Postorden: ${arbol.postOrder()}")
    println("¿Contiene 5? ${5 in arbol}")
    println("¿Contiene 20? ${arbol.contains(20)}")
    println("Altura del árbol: ${arbol.hight()}")
    println("Peso del árbol: ${arbol.weight()}")
    println("Nivel del 5: ${arbol.level(5)}")
    println("Número de nodos hoja: ${arbol.leafNodes()}")
    arbol.printTree()

    arbol.delete(5)
    println("Inorden tras borrar 5: ${arbol.inOrder()}")
    println("¿Contiene 5? ${5 in arbol}")
    println("Altura del árbol: ${arbol.hight()}")
    println("Peso del árbol: ${arbol.weight()}")
    println("Nivel del 5: ${arbol.level(5)}")
    println("Número de nodos hoja: ${arbol.leafNodes()}")
    println(arbol)


}
