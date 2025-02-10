package dev.joseluisgs

data class Persona(val nombre: String, val edad: Int) : Comparable<Persona> {
    override fun compareTo(other: Persona): Int {
        return this.edad - other.edad
    }
}
