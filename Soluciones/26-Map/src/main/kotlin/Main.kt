package dev.joseluisgs

import dev.joseluisgs.models.Vehiculo
import java.util.*

fun main() {
    val v1 = Vehiculo(5, "Carro", "Bonito", 2010, 10000.0)
    val v2 = Vehiculo(2, "Moto", "Rapido", 2020, 5000.0)
    val v3 = Vehiculo(8, "Carro", "Bonito", 2015, 60000.0)

    println("LinkedHashMap")
    val mapaVehiculo: MutableMap<String, Vehiculo> = mutableMapOf() // LinkedHashMap()
    mapaVehiculo["caquita"] = v1 // mapaVehiculo.put(v1.id, v1)
    mapaVehiculo["holahola"] = v2
    mapaVehiculo["adios"] = v3

    println(mapaVehiculo)
    println(mapaVehiculo["caca"])

    mapaVehiculo.forEach { println("${it.key} -> ${it.value}") }

    println("HashMap")
    val mapaVehiculo2: HashMap<String, Vehiculo> = hashMapOf()
    mapaVehiculo2["caquita"] = v1
    mapaVehiculo2["holahola"] = v2
    mapaVehiculo2["adios"] = v3

    println(mapaVehiculo2)
    println(mapaVehiculo2["caca"])

    mapaVehiculo2.forEach { println("${it.key} -> ${it.value}") }

    println("TreeMap")
    val mapaVehiculo3: SortedMap<String, Vehiculo> = sortedMapOf()
    mapaVehiculo3["caquita"] = v1
    mapaVehiculo3["holahola"] = v2
    mapaVehiculo3["adios"] = v3


    mapaVehiculo3.forEach { println("${it.key} -> ${it.value}") }


}

