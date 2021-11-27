package by.akella.shared

import kotlinx.coroutines.CoroutineDispatcher

expect object Dispatchers {

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher
}