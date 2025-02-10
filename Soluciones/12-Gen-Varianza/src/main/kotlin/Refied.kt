package dev.joseluisgs

// Los tipos genéricos no se pueden usar en tiempo de ejecución
// por lo que no podemos usar T::class.simpleName directamente
// para ello usamos el modificador reified que nos permite
// usar el tipo genérico en tiempo de ejecución

// Al ser inline el compilador sustituye el código en tiempo de compilación
// por lo que no se genera código extra, y el modificador reified nos permite
// usar el tipo genérico en tiempo de ejecución

/*
fun <T> printType() {
    println(T::class.simpleName)
}
*/

inline fun <reified T> printTypeRefied() {
    println(T::class.simpleName)
}

fun main() {
    /*printType<Int>()
    printType<String>()
    printType<Double>()
    printType<Long>()
*/

    printTypeRefied<Int>()
    printTypeRefied<String>()
    printTypeRefied<Double>()
    printTypeRefied<Long>()
    printTypeRefied<Array<String>>()
}