package by.akella.benefits.util

sealed class Resource<out T> {
    object None : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error(val error: CommonError) : Resource<Nothing>()
}

open class CommonError(val message: String, val e: Throwable = Exception())
