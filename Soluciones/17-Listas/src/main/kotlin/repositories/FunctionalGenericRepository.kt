package dev.joseluisgs.repositories

class CrudRepository<T : Any>(private val items: MutableList<T> = mutableListOf()) {

    // Create
    fun create(item: T): T {
        items.add(item)
        return item
    }

    // Read
    fun read(predicate: (T) -> Boolean): List<T> {
        return items.filter(predicate)
    }

    // Update
    fun update(predicate: (T) -> Boolean, transform: (T) -> T): List<T> {
        return items.mapIndexedNotNull { index, item ->
            if (predicate(item)) {
                items[index] = transform(item)
                items[index]
            } else {
                null
            }
        }
    }

    // Delete
    fun delete(predicate: (T) -> Boolean): List<T> {
        return items.filter(predicate).onEach {
            items.remove(it)
        }
    }

    // List all items
    fun listAll(): List<T> = items.toList()
}

// Ejemplo de uso
data class User(val id: Int, var name: String)

fun main() {
    val userRepository = CrudRepository<User>()

    // Crear usuarios
    userRepository.create(User(1, "Juan"))
    userRepository.create(User(2, "Ana"))
    userRepository.create(User(3, "Luis"))

    // Leer usuarios con un criterio específico
    val usersNamedAna = userRepository.read { it.name == "Ana" }
    println(usersNamedAna) // Debería imprimir todos los usuarios llamados "Ana"

    // usuario con id = 2
    val userWithId2 = userRepository.read { it.id == 2 }
    println(userWithId2) // Debería imprimir el usuario con id 2

    // Actualizar usuarios con un criterio específico
    userRepository.update({ it.id == 1 }) { it.copy(name = "Juan Actualizado") }

    // Eliminar usuarios con un criterio específico
    userRepository.delete { it.id == 2 }

    // Listar todos los usuarios
    val allUsers = userRepository.listAll()
    println(allUsers) // Debería imprimir todos los usuarios excepto el usuario con id 2
}
