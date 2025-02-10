package dev.joseluisgs

open class Persona(val nombre: String)

class Estudiante(nombre: String) : Persona(nombre)
class Profesor(nombre: String) : Persona(nombre)

class Caja<T>(item: T) { // Sin nada es invariante
    private var item: T = item

    fun get(): T {
        println("Obteniendo $item")
        return item
    }

    fun set(item: T) {
        println("Poniendo $item")
        this.item = item
    }
}

class CajaCovariante<out T>(item: T) { // con out es covariante, el generico solo se puede usar como salida
    private var item: T = item

    fun get(): T {
        println("Obteniendo $item")
        // Se puede hacer porque al esperar T, todso subtipp de T puede operar como T
        return item
    }
}

// De esta forma podemos pasar un subtipo como salida, porque es seguro poder asignarlo a un supertipo
class CajaContravariante<in T : Any> {
    private lateinit var item: T // Esto y lo de Any es una chapuza para que cuadre todo de la misma manera, pues has de escribirse de otra forma, pero eso no es lo importante
    fun set(item: T) {
        // Solo se puede usar como entrada
        println("Poniendo $item")
        // Se puede hacer porque al esperar T, todso subtipp de T puede operar como T
        this.item = item
    }
}


fun main() {
    val estudiante: Estudiante = Estudiante("Jose")
    val persona: Persona = Estudiante("Luis") // No hay error porque Persona es una superclase de Estudiante

    // Analisis de Varianza: Invariante

    val cajaEstudiante: Caja<Estudiante> =
        Caja(estudiante) // No hay error porque Estudiante es una subclase de Estudiante
    val r1: Estudiante = cajaEstudiante.get()
    val cajaPersona: Caja<Persona> = Caja(persona) // No hay error porque Persona es una superclase de Estudiante
    val r2: Persona = cajaPersona.get() // No hay error porque Persona es una superclase de Estudiante
    val cajaPersona2: Caja<Persona> = Caja(estudiante)
    val r3: Persona = cajaPersona2.get()
    //val cajaEstudiante2: Caja<Estudiante> = Caja(persona) // Error porque Persona no es una subclase de Estudiante
    //val r4: Estudiante = cajaPersona2.item // Error porque no se puede dividir un Estudiante por un Persona
    // cajaEstudiante.item = persona // Error porque no se puede asignar un Persona a un Estudiante


    // Es invariante porque no se puede pasar un supertipo como entrada
    // y no se puede pasar un subtipo como salida


    // Analisis de Varianza: Covariante

    val cajaEstudianteCovariante: CajaCovariante<Estudiante> =
        CajaCovariante(estudiante)
    val r5: Estudiante = cajaEstudianteCovariante.get()
    val cajaPersonaCovariante: CajaCovariante<Persona> =
        CajaCovariante(persona) // No hay error porque Persona es una superclase de Estudiante
    val r6: Persona = cajaPersonaCovariante.get() // No hay error porque Persona es una superclase de Estudiante
    val cajaPersonaCovariante2: CajaCovariante<Persona> = CajaCovariante(estudiante)
    val r7: Persona = cajaPersonaCovariante2.get() // No hay error porque Persona es una superclase de Estudiante

    // Por que se puede hacer esto?
    // La covarianza nos permite pasar un subtipo como salida,
    // Ya que la operación es segura, ya que la variable se establece para el tipo de retorno que esperamos
    // al ser un subtipo, no hay problema en que sea un subtipo de lo que esperamos
    // Y por lo tanto es segura la asignación

    // Analisis de Varianza: Contravariante

    val cajaEstudianteContravariante: CajaContravariante<Estudiante> = CajaContravariante()
    cajaEstudianteContravariante.set(estudiante)
    //cajaEstudianteContravariante.set(persona) // Error porque no se puede asignar un Persona a un Estudiante
    val cajaPersonaContravariante: CajaContravariante<Persona> = CajaContravariante()
    cajaPersonaContravariante.set(estudiante) // No hay error porque Estudiante es una subclase de Persona


}