package dev.joseluisgs.models

import dev.joseluisgs.locale.*
import java.time.LocalDate
import java.time.LocalDateTime

data class Vehiculo(
    val id: Long = NEW_ID,
    val matricula: String,
    var marca: String,
    var modelo: String,
    var fechaMatriculacion: LocalDate,
    var consumo: Double, // litros cada 100 km
    var kilometraje: Int,
    var precio: Double,
    var tipo: TipoVehiculo,
    var color: ColorVehiculo,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var isDeleted: Boolean = false,
) {

    companion object {
        const val NEW_ID = -1L
    }

    enum class TipoVehiculo {
        COCHE, MOTO, CAMION, FURGONETA
    }

    enum class ColorVehiculo {
        BLANCO, NEGRO, ROJO, AZUL, AMARILLO
    }

    override fun toString(): String {
        return "Vehiculo(id=$id, matricula='$matricula', marca='$marca', modelo='$modelo', fechaMatriculacion=${fechaMatriculacion.toLocalDate()}, consumo=${
            consumo.roundTo(
                2
            ).toLocalDecimal()
        }, kilometraje=${kilometraje.toLocalInteger()}, precio=${precio.toLocalMoney()}, tipoVehiculo=$tipo, colorVehiculo=$color, createdAt=$createdAt, updatedAt=$updatedAt, isDeleted=$isDeleted)"
    }
}