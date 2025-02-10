package dev.joseluisgs

import dev.joseluisgs.factories.ProcesosFactory
import dev.joseluisgs.models.Proceso
import dev.joseluisgs.services.Planificador
import dev.joseluisgs.tda.ColaProcesosPrioritariosMapArrayDeque

fun main() {
    println("Planificador de Procesos con prioridad")
    val misProcesos = mutableListOf<Proceso>()

    // Creamos 30 procesos
    for (i in 1..30) {
        misProcesos.add(ProcesosFactory.create())
    }

    // Creamos el planificador
    val planificadorTreeSet = Planificador(ColaProcesosPrioritariosTreeSet(), 2)
    val planificadorLista = Planificador(ColaProcesosPrioritariosLista(), 2)
    val planificadoSinPrioridad = Planificador(ColaProcesosLista(), 2)
    val planificadorMap = Planificador(ColaProcesosPrioritariosMap(), 2)
    val planificadorTreeMapList = Planificador(ColaProcesosPrioritariosTreeMapList(), 2)
    val planificadorTreeMapMap = Planificador(ColaProcesosPrioritariosTreeMapMap(), 2)
    val planificadorTreeMapArrayDeque = Planificador(ColaProcesosPrioritariosMapArrayDeque(), 2)

    // Agregamos los procesos
    misProcesos.forEach {
        planificadorTreeSet.agregarProceso(it.copy())
        planificadorLista.agregarProceso(it.copy())
        planificadoSinPrioridad.agregarProceso(it.copy())
        planificadorMap.agregarProceso(it.copy())
        planificadorTreeMapList.agregarProceso(it.copy())
        planificadorTreeMapMap.agregarProceso(it.copy())
        planificadorTreeMapArrayDeque.agregarProceso(it.copy())
    }

    println()
    println()


    // Ejecutamos los procesos conjuntos
    println("Ejecutando procesos con cola prioritaria basado en conjunto ordenado TreeSet")
    println("El planificador tiene ${planificadorTreeSet.size()} procesos")
    println("Los procesos son: $planificadorTreeSet")
    println()
    planificadorTreeSet.ejecutar()

    println()
    println()

    // Ejecutamos los procesos listas
    println("Ejecutando procesos con cola prioritaria basado en lista ")
    println("El planificador tiene ${planificadorLista.size()} procesos")
    println("Los procesos son: $planificadorLista")
    println()
    planificadorLista.ejecutar()

    println()
    println()

    // Ejecutamos los procesos sin prioridad
    println("Ejecutando procesos sin prioridad")
    println("El planificador tiene ${planificadoSinPrioridad.size()} procesos")
    println("Los procesos son: $planificadoSinPrioridad")
    println()
    planificadoSinPrioridad.ejecutar()

    println()
    println()

    // Ejecutamos los procesos con prioridad
    println("Ejecutando procesos con cola prioritaria basado en Map")
    println("El planificador tiene ${planificadorMap.size()} procesos")
    println("Los procesos son: $planificadorMap")
    println()
    planificadorMap.ejecutar()

    println()
    println()

    // Ejecutamos los procesos con prioridad
    println("Ejecutando procesos con cola prioritaria basado en TreeMap y List")
    println("El planificador tiene ${planificadorTreeMapList.size()} procesos")
    println("Los procesos son: $planificadorTreeMapList")
    planificadorTreeMapList.ejecutar()

    println()
    println()

    // Ejecutamos los procesos con prioridad basado en TreeMap y ArrayDeque
    println("Ejecutando procesos con cola prioritaria basado en TreeMap y ArrayDeque")
    println("El planificador tiene ${planificadorTreeMapArrayDeque.size()} procesos")
    println("Los procesos son: $planificadorTreeMapArrayDeque")
    planificadorTreeMapArrayDeque.ejecutar()

    println()
    println()

    // Ejecutamos los procesos con prioridad
    println("Ejecutando procesos con cola prioritaria basado en TreeMap y Map")
    println("El planificador tiene ${planificadorTreeMapMap.size()} procesos")
    println("Los procesos son: $planificadorTreeMapMap")
    planificadorTreeMapMap.ejecutar()

    println()

}