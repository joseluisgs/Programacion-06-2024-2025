package dev.joseluisgs.exceptions

sealed class VehiculoException(message: String) : Exception(message) {
    //class MarcaInvalidaException(marca: String) : VehiculoException("Marca '$marca' no válida")
    //class ModeloInvalidoException(modelo: String) : VehiculoException("Modelo '$modelo' no válido")
    //class FechaMatriculacionInvalidaException(fecha: String) : VehiculoException("Fecha '$fecha' no válida")
    class ValidationException(mensaje: String) : VehiculoException(mensaje)
    class NotFoundException(mensaje: String) : VehiculoException(mensaje)
    class AlreadyExistsException(mensaje: String) : VehiculoException(mensaje)
}


