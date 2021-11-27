package by.akella.shared

import kotlinx.coroutines.Dispatchers

actual class Dispatchers {

    fun io() = Dispatchers.IO

    fun main() = Dispatchers.Main
}