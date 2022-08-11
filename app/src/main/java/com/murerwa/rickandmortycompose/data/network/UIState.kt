package com.murerwa.rickandmortycompose.data.network

sealed class UIState<out T> {
    data class Success<out T>(val value: T) : UIState<T>()
    data class Error(
        val errorMessage: String?,
        val isNetworkError: Boolean = false
    ) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
}

//data class UIState(
//    val isLoading: Boolean = false,
//    val data: Any? = null,
//    val error: String = ""
//)