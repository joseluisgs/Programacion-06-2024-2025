package dev.joseluisgs.commons

data class MapNode<K, V>(var key: K, var value: V, var next: MapNode<K, V>? = null)