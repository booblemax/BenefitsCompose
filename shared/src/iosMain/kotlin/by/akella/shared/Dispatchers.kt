package by.akella.shared

import kotlinx.coroutines.Dispatchers

actual class Dispatchers {

    fun io() = Dispatchers.Main

    fun main() = Dispatchers.Main
}