package dev.joseluisgs.repositories.crud

interface CrudRepository<T, ID> {
    fun getAll(): List<T> // no quiero que se pueda modificar
    fun getById(key: ID): T?
    fun save(value: T): T
    fun update(key: ID, value: T): T?
    fun delete(key: ID): T?
}