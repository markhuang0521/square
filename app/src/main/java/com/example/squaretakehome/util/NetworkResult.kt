package com.example.squaretakehome.util

sealed class NetworkResult< out T> {
    data class Success< T>(val data: T): NetworkResult<T>()
    data class Error( val exception: Throwable): NetworkResult<Nothing>()

    /**
     * A map function that unwraps the Result wrapper and returns a top level object
     * for the success or failure.
     */
    fun <R : Any> map(successBlock: (T) -> R, errorBlock: (Throwable) -> R): R {
        return when (this) {
            is Success -> successBlock(data)
            is Error -> errorBlock(exception)
        }
    }
    fun <R : Any> mapToLce(): Lce<T> {
        return when (this) {
            is Success -> Lce.Content(data = data)
            is Error -> Lce.Error(exception = exception)
        }
    }
}

