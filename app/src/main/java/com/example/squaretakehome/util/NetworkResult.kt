package com.example.squaretakehome.util

sealed class NetworkResult< T> {
    data class Success< T>(val data: T): NetworkResult<T>()
    data class Error<T>(val errorMessage: String, val errorCode: Int? = null): NetworkResult<T>()
    class Loading<T>:NetworkResult< T>()

}

