package dev.joseluisgs.models

class Vehiculo(
    val marca: String,
    val modelo: String,
    val anio: Int,
    val precio: Double,
) {
    override fun toString(): String {
        return "Vehiculo(marca='$marca', modelo='$modelo', anio=$anio, precio=$precio)"
    }

    // Si queremos que dos vehículos sean iguales si tienen la misma marca y modelo
    // Debemos sobreescribir el comportamiento de equals y hashCode para que se comporten como queremos en los Set

    /*override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehiculo

        if (marca != other.marca) return false
        if (modelo != other.modelo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = marca.hashCode()
        result = 31 * result + modelo.hashCode()
        return result
    }
*/
}