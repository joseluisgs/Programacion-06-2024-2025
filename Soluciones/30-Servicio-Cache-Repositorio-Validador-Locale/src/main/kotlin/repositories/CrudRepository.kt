package dev.joseluisgs.repositories

interface CrudRepository<ID, T> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun save(entity: T): T
    fun update(id: ID, entity: T): T?
    fun delete(id: ID): T?
}