package dev.joseluisgs

interface IPrueba<T, R> {
    fun prueba(t: T): R
    fun prueba2(r: R): T
    fun prueba3(t: T, r: R): String
}

class PruebaGen<T, R> : IPrueba<T, R> {
    override fun prueba(t: T): R {
        TODO()
    }

    override fun prueba2(r: R): T {
        TODO()
    }

    override fun prueba3(t: T, r: R): String {
        TODO()
    }
}

fun <T, R> prueba(t: T): R {
    TODO()
}

interface IPrueba2<T> {
    fun prueba(t: T): Int
}

class PruebaGen2<Int> : IPrueba2<Int> {
    override fun prueba(t: Int): kotlin.Int {
        return 0
    }
}

// Acotar el tipo de T en base a una interfaz o clase
fun <T : IPrueba2<T>> prueba2(t: T): Int {
    return 0
}

fun interface Cantar {
    fun cantar()
}

class Canario : Cantar {
    override fun cantar() {
        println("Pio pio")
    }
}

interface Tocar {
    fun tocar()
}

class Guitarrista : Tocar {
    override fun tocar() {
        println("Tun tun tun")
    }
}

abstract class Orquesta {
    abstract fun tocar()
}

class Musico : Cantar, Tocar, Orquesta() {
    override fun cantar() {
        println("La la la")
    }

    override fun tocar() {
        println("Tun tun tun")
    }
}

// Acotar el tipo de T en base a una interfaz o clase
fun <T> prueba3(t: T) where T : Cantar, T : Tocar, T : Orquesta {
    t.cantar()
    t.tocar()
}

// Se traga cualquier cosa
fun <T> imprimir(valor: T) {
    println(valor)
}

// si no queremos nulos en el generico
fun <T : Any> imprimirNoNulo(valor: T) {
    println(valor)
}

// Esto es lo que se conoce como acotacion y extensión del tipo genérico


interface IAnimal
interface IDog {
    fun ladrar()
}

sealed class Animal(val nombre: String) : IAnimal
class Dog(nombre: String, edad: Int) : Animal(nombre), IDog {
    override fun ladrar() {
        println("Guau")
    }
}

class Cat(nombre: String, color: String) : Animal(nombre)

class CanicheRabioso(nombre: String, edad: Int) : IDog {
    override fun ladrar() {
        println("Guau")
    }
}

// Se puede acotar el tipo genérico a una clase o interfaz
// Solo son válidos los tipos que implementen la interfaz o hereden de la clase

// T debe ser un Animal
fun <T : Animal> imprimirCosa(animal: T) {
    println(animal)
}

// T debe implementar la interfaz IAnimal
fun <T : IAnimal> imprimirICosa(animal: T) {
    println(animal)
}

// T debe implementar la interfaz IDog
fun <T : IDog> imprimirIDog(animal: T) {
    println(animal)
}

// T debe ser un Animal y además implementar la interfaz IDog
fun <T> imprimirAnimal(animal: T) where T : Animal, T : IDog {
    println(animal)
}

fun main() {
    val prueba = PruebaGen<String, Int>()
    println(prueba.prueba("Hola"))
    println(prueba.prueba2(1))
    println(prueba.prueba3("Hola", 1))
    val i: Int = prueba("Hola")
    val prueba2 = PruebaGen2<Int>()
    println(prueba2.prueba(1))
    prueba3(Musico())

    val enteros = arrayOf(2, 3)
    enteros.filter { it % 2 == 0 }
}

