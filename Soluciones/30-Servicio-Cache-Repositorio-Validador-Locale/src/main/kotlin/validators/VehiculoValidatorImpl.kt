package dev.joseluisgs.validators

import dev.joseluisgs.exceptions.VehiculoException
import dev.joseluisgs.models.Vehiculo
import java.time.LocalDate

class VehiculoValidatorImpl : VehiculoValidator {
    override fun validate(vehiculo: Vehiculo) {
        // Matricula: 4 números y 3 letras
        if (!Regex("^[0-9]{4}[A-Z]{3}$").matches(vehiculo.matricula)) {
            throw VehiculoException.ValidationException("La matricula ${vehiculo.matricula} no es válida")
        }
        // Marca: No vacía
        if (vehiculo.marca.isBlank()) {
            throw VehiculoException.ValidationException("La marca no puede estar vacía")
        }
        // Modelo: No vacío
        if (vehiculo.modelo.isBlank()) {
            throw VehiculoException.ValidationException("El modelo no puede estar vacío")
        }
        // fecha de matriculación: entre 1970 y hoy
        if (vehiculo.fechaMatriculacion.year < 1970 || vehiculo.fechaMatriculacion.year > LocalDate.now().year) {
            throw VehiculoException.ValidationException("La fecha de matriculación ${vehiculo.fechaMatriculacion} no es válida")
        }
        // consumo, entre 0 y 100
        if (vehiculo.consumo < 0 || vehiculo.consumo > 100) {
            throw VehiculoException.ValidationException("El consumo ${vehiculo.consumo} no es válido")
        }
        // Kilometraje: entre 0 y 100_000_000
        if (vehiculo.kilometraje < 0 || vehiculo.kilometraje > 100_000_000) {
            throw VehiculoException.ValidationException("El kilometraje ${vehiculo.kilometraje} no es válido")
        }
        // Precio: mayor que 0
        if (vehiculo.precio <= 0) {
            throw VehiculoException.ValidationException("El precio ${vehiculo.precio} no es válido")
        }
    }
}