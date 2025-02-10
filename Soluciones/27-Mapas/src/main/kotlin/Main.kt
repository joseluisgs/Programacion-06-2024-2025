package dev.joseluisgs

import java.util.*

data class Person(val id: Int, val name: String, val age: Int)

fun main() {
    // Mapa de enteros
    val numbers = mapOf("one" to 1, "two" to 2, "three" to 3)
    println("Numbers: $numbers")
    println("Numbers[one]: ${numbers["one"]}")

    // Mapa de personas
    val people = mapOf(
        2 to Person(1, "José Luis", 25),
        1 to Person(2, "María", 20)
    )
    println("People: $people")
    println("People[1]: ${people[1]}")

    // recorrer el mapa
    for ((key, value) in people) {
        println("Key: $key, Value: $value")
    }

    for (p in people) {
        println("Key: ${p.key}, Value: ${p.value}")
    }

    // LinkedHashMap mantiene el orden de inserción
    val linkedMap = linkedMapOf("one" to 1, "two" to 2, "three" to 3)
    println("LinkedMap: $linkedMap")
    println("LinkedMap[one]: ${linkedMap["one"]}")

    // HashMap no mantiene el orden de inserción, pero es más eficiente
    val hashMap = hashMapOf("one" to 1, "two" to 2, "three" to 3)
    println("HashMap: $hashMap")
    println("HashMap[one]: ${hashMap["one"]}")

    val people2 = hashMapOf(
        2 to Person(1, "José Luis", 25),
        1 to Person(2, "María", 20)
    )
    println("People2: $people2")
    println("People2[1]: ${people2[1]}")
    println("People2[2]: ${people2[2]}")

    // TreeMap mantiene el orden de los elementos, en base al orden natural de las claves
    val treeMap = TreeMap<String, Int>()
    treeMap["three"] = 3
    treeMap["two"] = 2
    treeMap["one"] = 1
    println("TreeMap: $treeMap")
    println("TreeMap[one]: ${treeMap["one"]}")

    for ((key, value) in treeMap) {
        println("Key: $key, Value: $value")
    }
    for (p in treeMap) {
        println("Key: ${p.key}, Value: ${p.value}")
    }

    val people3 = TreeMap<Int, Person>()
    people3[2] = Person(1, "José Luis", 25)
    people3[1] = Person(2, "María", 20)
    println("People3: $people3")
    println("People3[1]: ${people3[1]}")
    println("People3[2]: ${people3[2]}")
    for ((key, value) in people3) {
        println("Key: $key, Value: $value")
    }
    for (p in people3) {
        println("Key: ${p.key}, Value: ${p.value}")
    }

    val people4 = TreeMap<Person, String>(compareBy { it.id })
    people4[Person(1, "José Luis", 25)] = "José Luis"
    people4[Person(2, "María", 20)] = "María"
    println("People4: $people4")
    println("People4[1]: ${people4[Person(1, "José Luis", 25)]}")
    println("People4[2]: ${people4[Person(2, "María", 20)]}")
    for ((key, value) in people4) {
        println("Key: $key, Value: $value")
    }
    for (p in people4) {
        println("Key: ${p.key}, Value: ${p.value}")
    }


}