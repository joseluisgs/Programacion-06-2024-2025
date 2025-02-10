package dev.joseluisgs

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Person(var name: String, var age: Int) {
    fun sayHello() {
        println("Hello, my name is $name and I am $age years old")
    }

    fun jump() {
        println("I am jumping")
    }
}

// Implementación de scope let con persona
fun Person.let(block: (Person) -> Unit) {
    // Aplicamos el bloque a la persona que le pasamos como parámetro
    // Por eso usamos it dentro del bloque
    return block(this)
}

// Implementación de scope run con persona
fun Person.run(block: Person.() -> Unit) {
    // return this.block()
    // es una función con receptor, por lo que podemos usar this
    // que es lo que le aplicamos el bloque a la persona
    return block()
}

// Implementación de scope with con persona
fun Person.with(receiver: Person, block: Person.() -> Unit) {
    // tenemos un receptor que es la persona
    // que le aplicamos el bloque (lambda con receptor o funciones con receptor)
    // Es como crear una función de extensión para persona con el bloque
    return receiver.block()
}

// Implementación de scope apply con persona
fun Person.apply(block: Person.() -> Unit): Person {
    // tenemos un receptor que es la persona
    // que le aplicamos el bloque (lambda con receptor o funciones con receptor)
    // Es como crear una función de extensión para persona con el bloque
    // y devolvemos la persona (this)
    block()
    return this
}

// Implementación de scope also con persona
fun Person.also(block: (Person) -> Unit): Person {
    // Aplicamos el bloque a la persona que le pasamos como parámetro
    // Por eso usamos it dentro del bloque
    block(this)
    return this
}

data class Persona(
    var nombre: String = "",
    var edad: Int = 0,
) {
    @OptIn(ExperimentalUuidApi::class)
    val uuid = Uuid.random()

    fun saludar() {
        println("Hola, me llamo $nombre y tengo $edad años")
    }
}


fun main() {
    val person = Person("Joe", 30)
    person.sayHello()
    person.jump()

    // Scope let
    // // Let se suele usar para comparaciones y realizar una accion,
    // el propio objeto es el receptor (adios if !=null!!)
    person.let {
        // usamos it para referirnos a la persona
        // que es el parámetro de la función que usa let
        it.name = "Let"
        it.sayHello()
        it.jump()
    }

    // Scope run
    // Run
    // nos permite inicializar y ejecutar un método del objeto,
    // el propio objeto es el receptor
    var r1 = person.run {
        // podemos usar el this para referirnos a la persona
        // que es el parámetro de la función que usa run
        // pero está implicito
        //this.name = "Run"
        name = "Run"
        sayHello()
        jump()
    }
    person.sayHello()

    // Scope with
    // With, nos permite cambiar el estado de un objeto, hay que indicarle el objeto
    with(person) {
        // necesitamos un receptor que es la persona
        // que es el parámetro de la función que usa with
        // y le aplicamos el bloque
        //person.name = "With"
        //this.name = "With"
        name = "With"
        age = 20
        // sayHello()
        // jump()
    }
    person.sayHello()

    // Scope apply
    val r2 = person.apply {
        // necesitamos un receptor que es la persona
        // que es el parámetro de la función que usa apply
        // y le aplicamos el bloque
        //person.name = "Apply"
        //this.name = "Apply"
        name = "Apply"
        age = 20
        //sayHello()
        //jump()
    }


    // Scope also
    // Also se usa para realizar una accion, posterior
    person.also {
        // usamos it para referirnos a la persona
        // que es el parámetro de la función que usa also
        it.name = "Also"
        it.sayHello()
    }.jump()


    // Run
    // nos permite inicializar y ejecutar un método del objeto,
    // el propio objeto es el receptor
    val p1 = Persona()
    p1.run {
        nombre = "Run"
        edad = 25
        saludar()
    }

    // With, nos permite cambiar el estado de un objeto,
    // hay que indicarle el objeto
    val p2 = Persona()
    with(p2) {
        nombre = "With"
        edad = 25
        //saludar()
    }
    p2.saludar()

    // Apply nos permite cambiar el estado de un objeto, el propio objeto es el receptor: ¡Anda la interfaz fluida!
    val p3 = Persona()
    p3.apply {
        nombre = "Apply"
        edad = 25
        // saludar()
    }.saludar()

    // Let se suele usar para comparaciones
    // y realizar una accion, el propio objeto es el receptor (adios if!!)
    val p4 = Persona()
    p4.let {
        it.nombre = "Let"
        it.saludar()
    }

    var p5: Persona? = null
    p5?.let {
        it.nombre = "Let2"
        it.saludar()
    }

    // Also se usa para realizar una accion, posterior
    var p6 = Persona()
    p2.apply {
        nombre = "Juan"
        edad = 25
    }.also {
        it.saludar()
    }

    val randomNull: String? = null
    // Por favor ya no!!! que no estais en Java
    if (randomNull != null) {
        println(randomNull.length)
    }

    // Usando Scope Functions incluso puedo simular un if else
    randomNull?.let {
        println(it.length)
    }.run { println("Hola") }
        .also { println("Adios") }


    // Usando Elvis, pero e implica excribir una opcion o no o llamadas seguras
    println(randomNull?.length ?: "No hay nada")
    println(randomNull?.length)

    val b: String = "Me gusta Kotlin"
    b.let {
        println("B no es null")
    }
    // Comento esto que si no me lo borra
    // si no mira la función de más abajo: ifElseConLetRun
    // ?: run {
    // println("B sí es null")
    // }

    // queda mas claro con un if
    if (b != null) {
        println("B no es null")
    } else {
        println("B sí es null")
    }

    // Swap sin also
    var a = 1
    var b = 2
    var tmp = a
    a = b
    b = tmp

    // Swap con also
    var var1 = 1
    var var2 = 2
    var1 = var2.also { var2 = var1 } // var1 = var2 y ademas var2 = var1
    println("var1 = $var1, var2 = $var2")

    val p9 = Persona().apply {
        nombre = "Juan"
        edad = 25
    }

}

fun ifElseConLetRun(person: Person?) {
    person?.let {
        it.name = "Let"
        it.sayHello()
    } ?: run {
        println("Person es null")
    }.also {
        println("Adios")
    }

    // Otra forma con if else
    if (person != null) {
        person.name = "Let"
        person.sayHello()
    } else {
        println("Person es null")
    }
    println("Adios")
}