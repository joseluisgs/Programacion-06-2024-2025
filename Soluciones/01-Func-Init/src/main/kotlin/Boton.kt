package dev.joseluisgs

class Boton {
    val background: String = "blue"
    var text: String = "Click me"
    fun onClick(action: () -> Unit) {
        action()
    }
}