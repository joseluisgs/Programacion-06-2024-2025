package dev.joseluisgs.repositories.personas

import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.crud.CrudRepository

class PersonasRepository : CrudRepository<Persona, Int> {
    private var personas = mutableListOf<Persona>()
    private var key: Int = 0

    override fun getAll(): List<Persona> {
        return personas.toList()
    }

    override fun getById(key: Int): Persona? {
        return personas.firstOrNull { it.id == key }
    }

    override fun delete(key: Int): Persona? {
        // sacamos su indice
        var persona: Persona? = null
        val index = personas.indexOfFirst { it.id == key }
        if (index != -1) {
            persona = personas[index]
            personas.removeAt(index)
        }
        return persona
    }

    override fun update(key: Int, value: Persona): Persona? {
        return getById(key)?.apply {
            name = value.name
            email = value.email
        }
    }

    override fun save(value: Persona): Persona {
        // Si el array está lleno (no tiene nulos)
        key++ // Incrementamos la clave
        value.id = key // Asignamos la clave
        personas.add(value) // Añadimos el valor
        return value // Devolvemos el valor
    }


}