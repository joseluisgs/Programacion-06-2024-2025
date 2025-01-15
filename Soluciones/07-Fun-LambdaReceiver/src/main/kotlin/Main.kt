package dev.joseluisgs

// Esto es una función normal de sumar
fun suma(a: Int, b: Int) = a + b

// Esto es una función de orden superior que recibe una función como parámetro
fun oppOrdenSuperior(a: Int, b: Int, accion: (Int, Int) -> Int): Int {
    return accion(a, b)
}

// Esto es una función de extensión que suma dos números
fun Int.sumaExtension(b: Int) = this + b

// Esto es una función de extensión de orden superior que recibe una función como parámetro
fun Int.oppExtensionOrdenSuperior(b: Int, accion: (Int, Int) -> Int): Int {
    return accion(this, b)
}

// Lambda con receiver
fun Int.oppLambdaReceiver(b: Int, accion: Int.(Int) -> Int): Int {
    return this.accion(b) // this es el contexto
    // return accion(b) // se puede omitir el this
}

fun Int.opp(operacion: Int.() -> Int): Int = operacion()

// Una de las principlaes ventajas
// es hacer los famosos Safe Builders
// que son funciones de orden superior que permiten
// construir estructuras de datos de forma segura

fun buildString(actions: StringBuilder.() -> Unit): String {
    val builder = StringBuilder()
    // Aquí usamos la función de extensión append de la clase StringBuilder y ejecutamos
    // el bloque de código que nos pasan como parámetro
    builder.actions()
    return builder.toString()
}

data class Persona(var nombre: String, var apellidos: String)

// builder con funcion de extensión
fun buildPersona(actions: Persona.() -> Unit): Persona {
    val persona = Persona("", "")
    persona.actions()
    return persona
}


fun main() {
    // Funcion normal de sumar
    println(suma(2, 3))
    // Funcion de orden superior que llama a la funcion normal de sumar
    println(oppOrdenSuperior(2, 3, ::suma))
    // Funcion de orden superior que llama a una funcion lambda que define la operacion
    println(oppOrdenSuperior(2, 3) { a, b -> a + b })
    // Funcion de extensión que suma dos números
    println(2.sumaExtension(3))
    // Funcion de extensión de orden superior que llama a la funcion normal de sumar
    println(2.oppExtensionOrdenSuperior(3) { a, b -> a + b })
    // Funcion con lambda con receiver
    println(2.oppLambdaReceiver(3) { this + it }) // Asi se ve regular :)
    println(2.oppLambdaReceiver(3) { this.plus(it) }) // Asi se ve algo mejor porque accedo al this, es decir al contexto
    // Funcion con lambda con receiver y operador
    println(2.opp { plus(3) }) // Asi se ve mejor porque accedo al this, es decir al contexto

    // Safe Builders
    println(buildString {
        append("Hola ")
        append("Mundo!")
    })

    val persona = buildPersona {
        nombre = "Jose Luis"
        apellidos = "García Sastre"
    }
    println(persona) // Persona(nombre=Jose Luis, apellidos=García Sastre)

    val persona2 = buildPersona {
        nombre = arrayOf("Jose", "Luis", "Ana", "Natalia").random()
        apellidos = arrayOf("García", "Sastre", "García Sastre").random()
    }
    println(persona2)

    val myTable = html {
        table {
            tr {
                td("Celda 1")
                td("Celda 2")
            }
            tr {
                td("Celda 3")
                td("Celda 4")
            }
        }
    }

    println(myTable)
}

// Opcional para ver como funciona los DSL en Kotlin
class HTML {
    private val elements = mutableListOf<Table>()

    fun table(init: Table.() -> Unit) {
        val table = Table().apply(init)
        elements.add(table)
    }

    override fun toString(): String {
        return elements.joinToString(
            separator = "\n",
            prefix = "<html>\n<body>\n",
            postfix = "\n</body>\n</html>"
        ) { it.toString() }
    }
}

class Table {
    private val rows = mutableListOf<Tr>()

    fun tr(init: Tr.() -> Unit) {
        val tr = Tr().apply(init)
        rows.add(tr)
    }

    override fun toString(): String {
        return rows.joinToString(separator = "\n", prefix = "<table>\n", postfix = "\n</table>") { it.toString() }
    }
}

class Tr {
    private val cells = mutableListOf<Td>()

    fun td(content: String) {
        val td = Td(content)
        cells.add(td)
    }

    override fun toString(): String {
        return cells.joinToString(separator = "", prefix = "  <tr>\n", postfix = "\n  </tr>") { it.toString() }
    }
}

class Td(private val content: String) {
    override fun toString(): String {
        return "    <td>$content</td>"
    }
}

fun html(init: HTML.() -> Unit): HTML {
    return HTML().apply(init)
}
