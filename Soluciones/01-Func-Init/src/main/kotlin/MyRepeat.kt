package dev.joseluisgs

/*
fun myRepeat(times: Int, action: () -> Unit) {
    for (i in 0 until times) {
        action()
    }
}*/

fun myRepeat(times: Int, action: (Int) -> Unit) {
    for (i in 0 until times) {
        action(i)
    }
}
