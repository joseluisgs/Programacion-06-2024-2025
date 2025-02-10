package dev.joseluisgs.factories

import dev.joseluisgs.models.Vehiculo
import java.time.LocalDate

object VehiculosFactory {
    fun demo(): List<Vehiculo> {
        return listOf(
            Vehiculo(
                matricula = "1234ABC",
                marca = "Toyota",
                modelo = "Corolla",
                fechaMatriculacion = LocalDate.of(2018, 1, 1),
                consumo = 6.5,
                kilometraje = 85000,
                precio = 15000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.ROJO
            ),
            Vehiculo(
                matricula = "5678DEF",
                marca = "Honda",
                modelo = "Civic",
                fechaMatriculacion = LocalDate.of(2017, 5, 10),
                consumo = 7.0,
                kilometraje = 90000,
                precio = 16000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.AZUL
            ),
            Vehiculo(
                matricula = "9101GHI",
                marca = "Ford",
                modelo = "Focus",
                fechaMatriculacion = LocalDate.of(2019, 3, 15),
                consumo = 6.8,
                kilometraje = 75000,
                precio = 14000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.NEGRO
            ),
            Vehiculo(
                matricula = "1121JKL",
                marca = "Toyota",
                modelo = "Prius",
                fechaMatriculacion = LocalDate.of(2020, 7, 20),
                consumo = 5.5,
                kilometraje = 60000,
                precio = 20000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.BLANCO
            ),
            Vehiculo(
                matricula = "3141MNO",
                marca = "Honda",
                modelo = "Accord",
                fechaMatriculacion = LocalDate.of(2016, 11, 25),
                consumo = 7.2,
                kilometraje = 95000,
                precio = 17000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.ROJO
            ),
            Vehiculo(
                matricula = "5161PQR",
                marca = "Ford",
                modelo = "Mustang",
                fechaMatriculacion = LocalDate.of(2021, 2, 5),
                consumo = 8.0,
                kilometraje = 50000,
                precio = 30000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.AZUL
            ),
            Vehiculo(
                matricula = "7181STU",
                marca = "Chevrolet",
                modelo = "Camaro",
                fechaMatriculacion = LocalDate.of(2015, 8, 30),
                consumo = 9.0,
                kilometraje = 120000,
                precio = 25000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.NEGRO
            ),
            Vehiculo(
                matricula = "9201VWX",
                marca = "Nissan",
                modelo = "Altima",
                fechaMatriculacion = LocalDate.of(2018, 4, 12),
                consumo = 6.9,
                kilometraje = 80000,
                precio = 18000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.BLANCO
            ),
            Vehiculo(
                matricula = "1023YZA",
                marca = "Mazda",
                modelo = "3",
                fechaMatriculacion = LocalDate.of(2019, 6, 18),
                consumo = 6.3,
                kilometraje = 70000,
                precio = 17000.0,
                tipo = Vehiculo.TipoVehiculo.MOTO,
                color = Vehiculo.ColorVehiculo.AMARILLO
            ),
            Vehiculo(
                matricula = "1123BCD",
                marca = "Hyundai",
                modelo = "Elantra",
                fechaMatriculacion = LocalDate.of(2017, 9, 22),
                consumo = 6.7,
                kilometraje = 85000,
                precio = 16000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.NEGRO
            ),
            Vehiculo(
                matricula = "1234EFG",
                marca = "Kia",
                modelo = "Optima",
                fechaMatriculacion = LocalDate.of(2016, 12, 5),
                consumo = 7.1,
                kilometraje = 95000,
                precio = 15000.0,
                tipo = Vehiculo.TipoVehiculo.CAMION,
                color = Vehiculo.ColorVehiculo.AZUL
            ),
            Vehiculo(
                matricula = "1345HIJ",
                marca = "Volkswagen",
                modelo = "Jetta",
                fechaMatriculacion = LocalDate.of(2018, 2, 14),
                consumo = 6.4,
                kilometraje = 80000,
                precio = 18000.0,
                tipo = Vehiculo.TipoVehiculo.FURGONETA,
                color = Vehiculo.ColorVehiculo.ROJO
            ),
            Vehiculo(
                matricula = "1456KLM",
                marca = "Subaru",
                modelo = "Impreza",
                fechaMatriculacion = LocalDate.of(2020, 10, 10),
                consumo = 6.2,
                kilometraje = 60000,
                precio = 20000.0,
                tipo = Vehiculo.TipoVehiculo.MOTO,
                color = Vehiculo.ColorVehiculo.BLANCO
            ),
            Vehiculo(
                matricula = "1567NOP",
                marca = "BMW",
                modelo = "3 Series",
                fechaMatriculacion = LocalDate.of(2019, 11, 30),
                consumo = 7.5,
                kilometraje = 70000,
                precio = 30000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.NEGRO
            ),
            Vehiculo(
                matricula = "1678QRS",
                marca = "Mercedes-Benz",
                modelo = "C-Class",
                fechaMatriculacion = LocalDate.of(2017, 3, 8),
                consumo = 7.8,
                kilometraje = 90000,
                precio = 35000.0,
                tipo = Vehiculo.TipoVehiculo.FURGONETA,
                color = Vehiculo.ColorVehiculo.AMARILLO
            ),
            Vehiculo(
                matricula = "1789TUV",
                marca = "Audi",
                modelo = "A4",
                fechaMatriculacion = LocalDate.of(2016, 7, 19),
                consumo = 7.3,
                kilometraje = 95000,
                precio = 32000.0,
                tipo = Vehiculo.TipoVehiculo.CAMION,
                color = Vehiculo.ColorVehiculo.BLANCO
            ),
            Vehiculo(
                matricula = "1890WXY",
                marca = "Lexus",
                modelo = "IS",
                fechaMatriculacion = LocalDate.of(2018, 5, 25),
                consumo = 6.6,
                kilometraje = 85000,
                precio = 28000.0,
                tipo = Vehiculo.TipoVehiculo.COCHE,
                color = Vehiculo.ColorVehiculo.AZUL
            ),
            Vehiculo(
                matricula = "1890XXY",
                marca = "Caca",
                modelo = "IS",
                fechaMatriculacion = LocalDate.of(2018, 5, 25),
                consumo = 6.6,
                kilometraje = 85000,
                precio = 38000.0,
                tipo = Vehiculo.TipoVehiculo.MOTO,
                color = Vehiculo.ColorVehiculo.AZUL
            ),
            Vehiculo(
                matricula = "1890WXX",
                marca = "Caca2",
                modelo = "IS",
                fechaMatriculacion = LocalDate.of(2018, 5, 25),
                consumo = 6.6,
                kilometraje = 85000,
                precio = 38000.0,
                tipo = Vehiculo.TipoVehiculo.CAMION,
                color = Vehiculo.ColorVehiculo.BLANCO
            ),
            Vehiculo(
                matricula = "1899WXX",
                marca = "Caca3",
                modelo = "IS",
                fechaMatriculacion = LocalDate.of(2018, 5, 25),
                consumo = 6.6,
                kilometraje = 85000,
                precio = 38000.0,
                tipo = Vehiculo.TipoVehiculo.FURGONETA,
                color = Vehiculo.ColorVehiculo.ROJO
            ),
        )
    }
}
