package dev.joseluisgs.models

class Vehiculo(
    val id: Int,
    val marca: String,
    val modelo: String,
    val anio: Int,
    val precio: Double,
) {
    override fun toString(): String {
        return "Vehiculo(id=$id, marca='$marca', modelo='$modelo', anio=$anio, precio=$precio)"
    }

    companion object {
        private var contador = 0
        fun nextId(): Int {
            contador++
            return contador
        }
    }
}