package services

import dev.joseluisgs.services.Calculadora
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CalculadoraTest {

    // TODO: Implementar los tests unitarios para las funciones de la calculadora
    // Definimos el SUBJECT UNDER TEST (SUT)
    // En este caso, el SUBJECT UNDER TEST es la Calculadora
    val calculadora = Calculadora()

    @Test
    @DisplayName("Al sumar dos números el resultado debería ser correcto")
    fun sumar() {
        // arrangement
        val num1 = 5.0
        val num2 = 3.0
        val expected = 8.0

        // act
        val resultado = calculadora.sumar(num1, num2)

        // assert
        assertEquals(expected, resultado, "El resultado de sumar $num1 y $num2 debería ser $expected")
    }

    @Test
    @DisplayName("Al restar dos números el resultado debería ser correcto")
    fun restar() {
        // arrangement
        val num1 = 5.0
        val num2 = 3.0
        val expected = 2.0

        // act
        val resultado = calculadora.restar(num1, num2)

        // assert
        assertEquals(expected, resultado, "El resultado de restar $num1 y $num2 debería ser $expected")
    }

    @Test
    @DisplayName("Al multiplicar dos números el resultado debería ser correcto")
    fun multiplicar() {
        // arrangement
        val num1 = 5.0
        val num2 = 3.0
        val expected = 15.0

        // act
        val resultado = calculadora.multiplicar(num1, num2)

        // assert
        assertEquals(expected, resultado, "El resultado de multiplicar $num1 y $num2 debería ser $expected")
    }

    @Test
    @DisplayName("Al dividir dos números el resultado debería ser correcto")
    fun dividir() {
        // arrangement
        val num1 = 6.0
        val num2 = 3.0
        val expected = 2.0

        // act
        val resultado = calculadora.dividir(num1, num2)

        // assert
        assertEquals(expected, resultado, "El resultado de dividir $num1 y $num2 debería ser $expected")
    }

    @Test
    @DisplayName("Al dividir por cero debería generar una excepción")
    fun dividirPorCero() {
        // arrangement
        val num1 = 6.0
        val num2 = 0.0


        // assert
        // La excepción debería ser lanzada
        val exception = assertThrows<IllegalArgumentException> {
            calculadora.dividir(num1, num2)
        }
        assertEquals("Divisor no puede ser 0", exception.message)
    }
}