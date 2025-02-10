package dev.joseluisgs

data class Persona(
    val nombre: String,
    val edad: Int,
    private val accionFunc: (String) -> Unit = { println(it) }
) {
    val esMayorDeEdad = edad >= 18

    val accion: () -> Unit
        get() = { accionFunc(nombre) }

}