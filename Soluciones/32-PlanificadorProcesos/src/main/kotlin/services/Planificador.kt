package dev.joseluisgs.services

import dev.joseluisgs.Cola
import dev.joseluisgs.models.Proceso
import kotlin.system.measureTimeMillis

class Planificador(
    private val colaProcesos: Cola<Proceso>,
    private val quatum: Int = 2,
    private var numEjecuciones: Int = 0,
    private var numTiempoEjecucion: Int = 0
) {

    fun agregarProceso(proceso: Proceso) {
        colaProcesos.enqueue(proceso)
    }

    fun ejecutar() {
        val time = measureTimeMillis {
            while (!colaProcesos.isEmpty()) {
                val proceso = colaProcesos.dequeue()
                println("Ejecutando proceso: $proceso")
                proceso?.let {
                    it.tiempo -= quatum
                    numEjecuciones++
                    numTiempoEjecucion += quatum
                    if (it.isActive) {
                        println("Proceso: $proceso, tiempo restante: ${it.tiempo}")
                        // Simulamos que el 25% de ls veces cambia la prioridad de 1..9
                        /*if ((1..100).random() <= 25) {
                            it.prioridad = (1..9).random()
                            println("Proceso: $proceso, cambio de prioridad a: ${it.prioridad}")
                        }*/
                        colaProcesos.enqueue(it)
                    } else {
                        println("Proceso: $proceso, terminado")
                    }
                }
            }
        }
        println("Numero de ejecuciones: $numEjecuciones")
        println("Numero de tiempo de ejecucion: $numTiempoEjecucion")
        println("Tiempo total de ejecucion: $time")
    }

    fun size(): Int {
        return colaProcesos.size()
    }

    override fun toString(): String {
        return colaProcesos.toString()
    }
}
