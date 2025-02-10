# Implementación de Caché con Políticas FIFO y LRU

Este proyecto proporciona una implementación de caché en Kotlin utilizando dos políticas de reemplazo populares: FIFO (
First-In-First-Out) y LRU (Least Recently Used). Ambas implementaciones utilizan `LinkedHashMap` para gestionar el
almacenamiento de los elementos de manera eficiente.

## Políticas de Reemplazo

### FIFO (First-In-First-Out)

La política FIFO elimina el elemento más antiguo de la caché cuando se necesita espacio para un nuevo elemento. Los
elementos se mantienen en el orden en que fueron insertados.

#### Configuración con LinkedHashMap

Para implementar FIFO, `LinkedHashMap` se utiliza con el modo de acceso por inserción. Esto se logra configurando el
tercer parámetro del constructor de `LinkedHashMap` a `false`:

```kotlin
private val cache = object : LinkedHashMap<K, V>(capacity, 0.75f, false) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        // Si el tamaño supera la capacidad, eliminamos el elemento más antiguo en insertarse, es decir
        // el que se encuentra al principio de la lista
        // Que será el primero de la lista
        logger.debug { "Desalojando la clave más antigua: ${entries.first().key}" }
        return size > capacity
    }
}
```

#### Pros:

- **Simplicidad**: La implementación es simple y directa, ya que solo se necesita mantener el orden de inserción.
- **Determinismo**: El comportamiento es predecible, ya que siempre se elimina el elemento más antiguo.

#### Contras:

- **Eficiencia de Uso**: No considera la frecuencia o la recencia de uso de los elementos, lo que puede llevar a
  eliminar elementos que aún son relevantes o útiles.
- **Desempeño Subóptimo**: En escenarios donde ciertos elementos se utilizan con más frecuencia, FIFO puede no ser la
  mejor opción.

### LRU (Least Recently Used)

La política LRU elimina el elemento menos recientemente utilizado cuando se necesita espacio para un nuevo elemento. Los
elementos se reordenan cada vez que se accede a ellos.

#### Configuración con LinkedHashMap

Para implementar LRU, LinkedHashMap se utiliza con el modo de acceso por uso. Esto se logra configurando el tercer
parámetro del constructor de LinkedHashMap a true:

```kotlin
private val cache = object : LinkedHashMap<K, V>(capacity, 0.75f, true) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        // Si el tamaño supera la capacidad, eliminamos el elemento más antiguo, es decir
        // el que se encuentra al principio de la lista
        // Que será el primero de la lista
        logger.debug { "Desalojando clave menos recientemente usada: ${entries.first().key}" }
        return size > capacity
    }
}
```

#### Pros:

- **Eficiencia de Uso**: Tiende a mantener en la caché los elementos que se utilizan con más frecuencia.
- **Optimización de Recursos**: Mejora el desempeño en escenarios donde ciertos elementos son accedidos repetidamente.

#### Contras:

- **Complejidad**: Puede ser más complejo de implementar que FIFO debido a la necesidad de reordenar elementos basándose
  en el acceso.

## Ventajas de LinkedHashMap

### Sobre Implementaciones de FIFO con una Cola

- **Eficiencia**: `LinkedHashMap` mantiene automáticamente el orden de inserción, eliminando la necesidad de gestionar
  una estructura de datos adicional como una cola.
- **Simplicidad**: Reduce la complejidad del código al manejar tanto el almacenamiento como el orden de los elementos en
  una sola estructura.

### Sobre Implementaciones de LRU con Huellas de Tiempo

- **Desempeño**: `LinkedHashMap` con orden de acceso permite reordenar elementos automáticamente cuando se acceden, sin
  necesidad de actualizar manualmente huellas de tiempo.
- **Mantenimiento**: Al evitar el uso de huellas de tiempo, se reduce la sobrecarga de mantenimiento de datos
  adicionales y se simplifica la lógica de acceso.

## Conclusión

El uso de `LinkedHashMap` en estas implementaciones de caché proporciona una solución eficiente y sencilla para
gestionar el almacenamiento y el orden de los elementos. Aprovecha las capacidades nativas de Kotlin para mantener el
orden de inserción o acceso, lo que facilita la implementación de políticas como FIFO y LRU sin necesidad de estructuras
adicionales o lógica compleja.
