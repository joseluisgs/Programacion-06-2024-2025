package repositories

import dev.joseluisgs.models.Alumno
import dev.joseluisgs.repositories.AlumnosRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Solo si se usa @BeforeAll o @AfterAll
class AlumnosRepositoryTest {

    // Sut
    val repository = AlumnosRepository()

    @BeforeAll
    fun setupClass() {
        // Establecer un estado inicial para todos los tests en la clase
        // Por ejemplo, inicializar la base de datos o cargar datos de prueba
        println("Antes de todos los tests en la clase")
    }

    @AfterAll
    fun tearDownClass() {
        // Limpiar el estado inicial para todos los tests en la clase
        // Por ejemplo, vaciar la base de datos o eliminar datos de prueba
        println("Después de todos los tests en la clase")
    }

    @BeforeEach
    fun setup() {
        // Establecer un estado inicial para los tests
        // Por ejemplo, inicializar la base de datos o cargar datos de prueba
        println("Antes de cada test")
        val alumnos = mutableListOf(
            Alumno("Juan", 8.0),
            Alumno("Maria", 9.0),
            Alumno("Pedro", 4.0)

        )
        repository.addAll(alumnos) // Cargar los alumnos de prueba en la base de datos
    }

    @AfterEach
    fun tearDown() {
        // Limpiar el estado de los tests para evitar inconsistencias
        // Por ejemplo, vaciar la base de datos o eliminar datos de prueba
        println("Después de cada test")
        repository.clear() // Limpiar la base de datos para cada test
    }

    @Test
    @DisplayName("Test de obtención de todos los alumnos")
    fun getAll() {
        // arrangement
        val expected = 3

        // act
        val actual = repository.getAll().size

        // assert
        assertEquals(expected, actual, "Debería obtener $expected alumnos")
    }

    @Test
    @DisplayName("Test de guardado de un alumno con todos los campos correctos")
    fun saveTodosCamposCorrectos() {
        // arrangement
        val alumno = Alumno("Ana", 7.0)

        // act
        val res = repository.save(alumno)
        val size = repository.getAll().size


        // assert
        assertAll(
            { assertEquals("Ana", res.nombre, "El nombre debería ser Ana") },
            { assertEquals(7.0, res.calificacion, "La calificación debería ser 7.0") },
            { assertEquals(4, size, "Debería haber 4 alumnos después de guardar un nuevo alumno") },
        )
    }

    @Test
    @DisplayName("Test de guardado de un alumno con un nombre vacío")
    fun saveNombreVacio() {
        // arrangement
        val alumno = Alumno("", 7.0)


        // assert
        val res = assertThrows<IllegalArgumentException> {
            // Act
            repository.save(alumno)
        }

        // assert
        assertEquals("El nombre del alumno no puede estar vacío", res.message)
    }

    @Test
    @DisplayName("Test de guardado de un alumno con una calificación menor a 0")
    fun saveCalificacionFueraDeRango() {
        // arrangement
        val alumno = Alumno("Jose", -1.0)

        // assert
        val res = assertThrows<IllegalArgumentException> {
            // Act
            repository.save(alumno)
        }

        // assert
        assertEquals("La calificación debe estar entre 0 y 10", res.message)
    }

    @Test
    @DisplayName("Test de guardado de un alumno con una calificación menor a 0")
    fun saveCalificacionMayorA10() {
        // arrangement
        val alumno = Alumno("Jose", 11.0)

        // assert
        val res = assertThrows<IllegalArgumentException> {
            // Act
            repository.save(alumno)
        }

        // assert
        assertEquals("La calificación debe estar entre 0 y 10", res.message)
    }

    @Test
    @DisplayName("Test de eliminación de un alumno que existe")
    fun deleteSiExiste() {
        // arrangement
        val alumno = "Juan"
        val expected = 2

        // act
        repository.delete(alumno)
        val actual = repository.getAll().size
        val todos = repository.getAll()

        // assert
        assertEquals(expected, actual, "Debería haber $expected alumnos después de eliminar un alumno")
        assertAll(
            { assertEquals("Maria", todos[0].nombre, "El nombre debería ser Maria") },
            { assertEquals("Pedro", todos[1].nombre, "El nombre debería ser Pedro") },
        )
    }

    @Test
    @DisplayName("Test de eliminación de un alumno que no existe")
    fun deleteSiNoExiste() {
        // arrangement
        val alumno = "Ana"

        // act
        repository.delete(alumno)
        val actual = repository.getAll().size

        // assert
        assertEquals(3, actual, "Debería seguir teniendo 3 alumnos después de eliminar un alumno que no existe")

        assertAll(
            { assertEquals("Juan", repository.getAll()[0].nombre, "El nombre debería ser Juan") },
            { assertEquals("Maria", repository.getAll()[1].nombre, "El nombre debería ser Maria") },
            { assertEquals("Pedro", repository.getAll()[2].nombre, "El nombre debería ser Pedro") },
        )
    }

    @Test
    fun eliminarAlumnoConNombreEnBlancoExcepcion() {
        // arrangement
        val alumno = ""

        // assert
        val res = assertThrows<IllegalArgumentException> {
            // Act
            repository.delete(alumno)
        }

        // assert
        assertEquals("El nombre del alumno no puede estar vacío", res.message)
    }

    @Test
    fun promedioCalificaciones() {
        // arrangement
        val expected = 7.0

        // act
        val actual = repository.promedioCalificaciones()

        // assert
        assertEquals(expected, actual, "El promedio de calificaciones debería ser 7.0")
    }
}