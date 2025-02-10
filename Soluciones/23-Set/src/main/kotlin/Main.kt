package dev.joseluisgs

import dev.joseluisgs.models.Vehiculo
import dev.joseluisgs.models.VehiculoComparable
import java.util.*

fun main() {
    val v1 = Vehiculo("Carro", "Bonito", 2010, 10000.0)
    val v2 = Vehiculo("Moto", "Rapido", 2020, 5000.0)
    val v3 = Vehiculo("Carro", "Bonito", 2015, 60000.0)

    println("Listas")
    val listaVehiculo: MutableList<Vehiculo> = mutableListOf()  // Es un ArrayList, prima el acceso por índice

    listaVehiculo.add(v1)
    listaVehiculo.add(v2)
    listaVehiculo.add(v3)

    repeat(5) {
        if (!listaVehiculo.contains(v1)) {
            listaVehiculo.add(v1)
        }
    }

    listaVehiculo.forEach {
        println(it)
    }

    /*val lista = listaVehiculo.sumOf { it.precio }
    println("Total: $lista")*/

    println("Conjuntos LinkedHashSet")

    val setVehiculo: MutableSet<Vehiculo> =
        mutableSetOf() // Es un LinkedHashSet, prima que no haya elementos repetidos y el orden de inserción

    setVehiculo.add(v1)
    setVehiculo.add(v2)
    setVehiculo.add(v3)

    repeat(5) {
        setVehiculo.add(v1)
    }

    setVehiculo.forEach {
        println(it)
    }

    println("Conjuntos HashSet")
    val hashSet: MutableSet<Vehiculo> = hashSetOf() // Es un HashSet, prima que no haya elementos repetidos
    hashSet.add(v1)
    hashSet.add(v2)
    hashSet.add(v3)

    repeat(5) {
        hashSet.add(v1)
    }

    hashSet.forEach {
        println(it)
    }

    println("Conjuntos TreeSet Comparable")
    val treeSet: MutableSet<VehiculoComparable> =
        TreeSet() // Es un TreeSet, prima que no haya elementos repetidos, en base a un criterio de orden en el Comparable de la clase
    val vc1 = VehiculoComparable("Carro", "Bonito", 2010, 10000.0)
    val vc2 = VehiculoComparable("Moto", "Rapido", 2020, 5000.0)
    val vc3 = VehiculoComparable("Carro", "Bonito", 2015, 60000.0)

    treeSet.add(vc1)
    treeSet.add(vc2)
    treeSet.add(vc3)

    repeat(5) {
        treeSet.add(vc1)
    }

    treeSet.forEach {
        println(it)
    }

    println("Conjuntos TreeSet Comparator")
    val comparatorSet: MutableSet<Vehiculo> =
        TreeSet { o1, o2 ->
            o1.precio.compareTo(o2.precio)
        } // Es un TreeSet, prima que no haya elementos repetidos, en base a un criterio de orden en el Comparator

    comparatorSet.add(v1)
    comparatorSet.add(v2)
    comparatorSet.add(v3)

    repeat(5) {
        comparatorSet.add(v1)
    }

    comparatorSet.forEach {
        println(it)
    }


}

