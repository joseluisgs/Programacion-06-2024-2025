package dev.joseluisgs.models

class VehiculoComparable(
    val marca: String,
    val modelo: String,
    val anio: Int,
    val precio: Double,
) : Comparable<VehiculoComparable> {
    override fun compareTo(other: VehiculoComparable): Int {
        if (this.precio > other.precio) {
            return 1
        } else if (this.precio < other.precio) {
            return -1
        }
        return 0

    }

    override fun toString(): String {
        return "Vehiculo(marca='$marca', modelo='$modelo', anio=$anio, precio=$precio)"
    }

}