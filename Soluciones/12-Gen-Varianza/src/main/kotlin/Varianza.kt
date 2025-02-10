package dev.joseluisgs


// https://kotlinlang.org/docs/generics.html
// https://www.baeldung.com/kotlin/generics

open class Persona(val nombre: String)
class Profesor(nombre: String, val materia: String) : Persona(nombre)

class BoxInv<T>(t: T) {
    var value = t

    fun get(): T {
        return value
    }

    fun set(t: T) {
        value = t
    }
}

class BoxCov<out T>(val t: T) {

    fun get(): T {
        return t
    }

    /* fun set(t: T) {
         value = t
     }*/
}

class BoxContv<in T> {
    var value: Any? = null

    /* fun get(): T {
         return t
     }*/

    fun set(t: T) {
        value = t
    }
}

// Podemos tener covarianza y contravarianza en la misma clase
// En este caso, el tipo de retorno es covariante y el tipo de entrada es contravariante
// ademas R es un subtipo de Number
class BoxContCov<in T, out R : Number> {
    var value: Any? = null

    fun get(): R {
        return value as R // Ciertamente esto es un poco raro, pero es para que se entienda
    }

    fun set(t: T) {
        value = t
    }
}

// Copiar Array of Subtipos a un Array of Supertipos
// Array<out T> es un Array de T de solo lectura
// Out, cualquier cosa por encima de Any, es lo cumple Any?


fun copy1(from: Array<out Any>, to: Array<Any?>) {
    for (i in from.indices)
        to[i] = from[i]
}

// Añadir elementos de un subtipo a un Array de supertipo
// Array<in T> es un Array de T donde vamos a producir
// con in decimos que como máximo un Int
fun fill(dest: Array<in Int>, value: Int) {
    dest[0] = value
}

/*
Hay situaciones en las que no nos importa el tipo específico de valor.
Digamos que solo queremos imprimir todos los elementos de un Array y no importa cuál
sea el tipo de elementos en este Array.
 */

fun printArray(array: Array<*>) {
    array.forEach { println(it) }
}

class Encrypted<in T> {
    val key: String = "1234567890"
    fun encrypt(value: T): String {
        return value.hashCode().toString() + key
    }

}


fun main() {
    val persona: Persona = Profesor("Pepe", "Matemáticas")
    println(persona.nombre)

    // Esto no se puede hacer
    // val profesor: Profesor = Persona("Pepe")
    // println(profesor.nombre)

    // Varianza con arrays
    val profesores: Array<Profesor> = arrayOf(
        Profesor("Pepe", "Matemáticas"),
        Profesor("Juan", "Lengua")
    )
    val personas: Array<Persona> = profesores as Array<Persona>
    println(personas[0].nombre)
    val p1: Persona = personas[0]

    val personas2: Array<Persona> = arrayOf(
        Persona("Pepe"),
        Persona("Juan"),
        Profesor("Pepe", "Matemáticas"),
    )
    val profesores2: Array<Profesor> = personas2 as Array<Profesor>
    println(profesores2[0].nombre)
    val p2: Profesor = profesores2[0]

    // val integerList = arrayOf(1, 2, 3)
    //val numberList: Array<Number> = integerList; // esto no es posible porque Array<Int> no es subtipo de Array<Number>

    imprimirPersona(Profesor("Pepe", "Matemáticas"))
    imprimirPersonas(profesores)

    var p4: Persona = Persona("Pepe")
    var p5: Profesor = Profesor("Pepe", "Matemáticas")
    p4 = p5 // Esto es posible porque Profesor hereda de Persona

    val box: BoxInv<Persona> = BoxInv(p4)
    val box2: BoxInv<Profesor> = BoxInv(p5)
    // val box3: BoxInv<Persona> = box2// Esto no es posible, porque BoxInv<Profesor> no es subtipo de BoxInv<Persona> (invariante)

    val box4: BoxCov<Persona> = BoxCov(p4)
    val box5: BoxCov<Profesor> = BoxCov(p5)
    val box6: BoxCov<Persona> =
        box5 // Esto es posible, porque Box<Profesor> es subtipo de Box<Persona> (covariante), el generico solo aparece en posiciones de retorno

    val box7: BoxContv<Persona> = BoxContv()
    box7.set(p4)
    val box8: BoxContv<Profesor> = BoxContv()
    box8.set(p5)
    val box9: BoxContv<Profesor> =
        box7 // Estoes posible, porque BoxContv<Persona> no es subtipo de BoxContv<Profesor> (contravariante), el generico solo aparece en posiciones de entrada

    val ints = arrayOf(1, 2, 3)
    val any = arrayOfNulls<Any>(3)
    copy1(ints, any)
    println(any.contentToString())

    val objects: Array<Any?> = arrayOfNulls(1)
    fill(objects, 1)

    val array = arrayOf(1, 2, 3)
    printArray(array)

    var encryptedProfesor: Encrypted<Profesor> = Encrypted<Profesor>()
    encryptedProfesor.encrypt(p5)
    var encryptedPersona: Encrypted<Persona> = Encrypted<Persona>()
    encryptedPersona.encrypt(p4)
    encryptedPersona.encrypt(p5)
    // encryptedPersona = encryptedProfesor // --> Esto no es posible porque Encrypted<Profesor> no es subtipo de Encrypted<Persona> (contravariante)
    encryptedProfesor = encryptedPersona
    encryptedPersona.encrypt(p4)
    encryptedPersona.encrypt(p5)

    val enteros: Array<Int> = arrayOf(1, 2, 3)
    // trick(enteros) // --> Esto no es posible porque Array<Int> no es subtipo de Array<Any> (invariante)

}

fun imprimirPersona(persona: Persona) {
    println(persona.nombre)
}

fun imprimirPersonas(personas: Array<Persona>) {
    personas.forEach { println(it.nombre) }
}

private fun trick(objects: Array<Any>) {
    objects[0] = 123 // ok
    objects[1] = Any() // ArrayStoreException
    // thrown at runtime
}

