package com.murerwa.rickandmortycompose.presentation.screens.characters

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortycompose.data.network.NetworkResult
import com.murerwa.rickandmortycompose.data.network.UIState
import com.murerwa.rickandmortycompose.domain.models.characters.CharactersResponse
import com.murerwa.rickandmortycompose.domain.repositories.CharactersRepository
import com.murerwa.rickandmortycompose.presentation.readError
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _charactersResponse: MutableState<UIState<CharactersResponse>> = mutableStateOf(UIState.Loading)
    val charactersResponse= _charactersResponse

    init {
        getCharacters()
    }

     private fun getCharacters() {
        viewModelScope.launch {
            _charactersResponse.value = UIState.Loading

            when (val response = repository.getCharacters(page = 1)) {
                is NetworkResult.Success -> {
                    _charactersResponse.value = UIState.Success(response.value)
                }
                is NetworkResult.Failure -> {
                    if (response.isNetworkError) {
                        _charactersResponse.value = UIState.Error("Network error")
                    } else {
                        if (response.errorBody != null) {
                            Timber.d("Response is Not Null")
                            val error = response.errorBody.readError()
                            if (!error.isNullOrEmpty()) {
                                _charactersResponse.value = UIState.Error(error)
                            } else {
                                _charactersResponse.value = UIState.Error("Error fetching characters")
                            }

                        } else {
                            Timber.d("Response is Null")
                            _charactersResponse.value = UIState.Error("Error fetching characters")
                        }
                    }
                }
            }
        }
    }

    fun loadMoreCharacters(previousPage: Int) {

        val nextPage = previousPage + 1

        viewModelScope.launch {

            when (val response = repository.getCharacters(page = nextPage)) {
                is NetworkResult.Success -> {
                    _charactersResponse.value.let {
                        if (it is UIState.Success) {
                            _charactersResponse.value =
                                UIState.Success(
                                    it.value.copy(results = it.value.results + response.value.results),
                                    currentPage = nextPage,
                                    isRefreshing = false
                                )
                        }
                    }
                }
                is NetworkResult.Failure -> {
                    _charactersResponse.value.let {
                        if (it is UIState.Success) {
                            _charactersResponse.value =
                                UIState.Success(
                                    it.value.copy(
                                        results = it.value.results,
                                    ),
                                    isLoadingMoreError = "Could not load more items",
                                    currentPage = nextPage,
                                    isRefreshing = false
                                )
                        }
                    }
                }
            }
        }
    }
}