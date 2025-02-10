package dev.joseluisgs.models

data class Persona(var name: String, var email: String) {
    var id: Int = 0

    override fun toString(): String {
        return "Persona(id=$id, name='$name', email='$email')"
    }
}