package dev.joseluisgs.validators

interface Validator<T> {
    fun validate(entity: T)
}