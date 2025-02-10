package services

import dev.joseluisgs.services.ListaService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ListaServiceTest {
    // TODO: Implementar los tests unitarios para la clase ListaService
    // Definimos el SUBJECT UNDER TEST (SUT)
    // En este caso, el SUBJECT UNDER TEST es la ListaService
    val listaService = ListaService()

    @Test
    @DisplayName("Encontrar mínimo con elementos")
    fun encontrarMinimoConElementos() {
        // arrangement
        val lista = listOf(5, 3, 2, 8, 1)
        val expected = 1

        // act
        val resultado = listaService.encontrarMinimo(lista)

        // assert
        assertEquals(expected, resultado, "El mínimo de la lista debería ser $expected")
    }

    @Test
    @DisplayName("Encontrar mínimo sin elementos")
    fun encontrarMinimoSinElementos() {
        // arrangement
        val lista: List<Int> = emptyList()
        val expected = null

        // act
        val resultado = listaService.encontrarMinimo(lista)

        // assert
        assertEquals(expected, resultado, "El mínimo de una lista vacía debería ser infinito")
    }

    @Test
    @DisplayName("Encontrar máximo con elementos")
    fun encontrarMaximoConElementos() {
        // Arrangement
        val lista = listOf(5, 3, 2, 8, 1)
        val expected = 8

        // Act
        val resultado = listaService.encontrarMaximo(lista)

        // Assert
        assertEquals(expected, resultado, "El máximo de la lista debería ser $expected")
    }

    @Test
    @DisplayName("Encontrar máximo sin elementos")
    fun encontrarMaximoSinElementos() {
        // Arrangement
        val lista: List<Int> = emptyList()
        val expected = null

        // Act
        val resultado = listaService.encontrarMaximo(lista)

        // Assert
        assertEquals(expected, resultado, "El máximo de una lista vacía debería ser infinito")
    }

    @Test
    @DisplayName("Sumar elementos con elementos")
    fun sumarElementosConElementos() {
        // Arrangement
        val lista = listOf(5, 3, 2, 8, 1)
        val expected = 19

        // Act
        val resultado = listaService.sumarElementos(lista)

        // Assert
        assertEquals(expected, resultado, "La suma de la lista debería ser $expected")
    }

    @Test
    @DisplayName("Sumar elementos sin elementos")
    fun sumarElementosSinElementos() {
        // Arrangement
        val lista: List<Int> = emptyList()
        val expected = 0

        // Act
        val resultado = listaService.sumarElementos(lista)

        // Assert
        assertEquals(expected, resultado, "La suma de una lista vacía debería ser null")
    }

    @Test
    @DisplayName("Promedio con elementos")
    fun promedioConElementos() {
        // Arrangement
        val lista = listOf(5, 3, 2, 8, 1)
        val expected = 3.8

        // Act
        val resultado = listaService.promedioElementos(lista)

        // Assert
        assertEquals(expected, resultado, "El promedio de la lista debería ser $expected")
    }

    @Test
    @DisplayName("Promedio sin elementos")
    fun promedioSinElementos() {
        // Arrangement
        val lista: List<Int> = emptyList()
        val expected = Double.NaN

        // Act
        val resultado = listaService.promedioElementos(lista)

        // Assert
        assertEquals(expected, resultado, "El promedio de una lista vacía debería ser null")
    }
}