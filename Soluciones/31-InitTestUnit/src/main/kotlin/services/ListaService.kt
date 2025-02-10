package dev.joseluisgs.services

class ListaService {
    fun encontrarMinimo(lista: List<Int>): Int? {
        return lista.minOrNull()
    }

    fun encontrarMaximo(lista: List<Int>): Int? {
        return lista.maxOrNull()
    }

    fun sumarElementos(lista: List<Int>): Int {
        return lista.sum()
    }

    fun promedioElementos(lista: List<Int>): Double {
        return lista.average()
    }
}