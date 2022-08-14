package com.murerwa.rickandmortycompose.presentation.screens.characterdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.rickandmortycompose.data.network.NetworkResult
import com.murerwa.rickandmortycompose.data.network.UIState
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.domain.models.characters.CharactersResponse
import com.murerwa.rickandmortycompose.domain.repositories.CharactersRepository
import com.murerwa.rickandmortycompose.presentation.readError
import kotlinx.coroutines.launch
import timber.log.Timber

class CharacterDetailsViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _characterDetailsResponse: MutableState<UIState<CharacterItem>> = mutableStateOf(UIState.Loading)
    val characterDetailsResponse= _characterDetailsResponse

     private fun getCharacters(characterId: Int) {
        viewModelScope.launch {
            _characterDetailsResponse.value = UIState.Loading

            when (val response = repository.getCharacterDetails(characterId)) {
                is NetworkResult.Success -> {
                    _characterDetailsResponse.value = UIState.Success(response.value)
                }
                is NetworkResult.Failure -> {
                    if (response.isNetworkError) {
                        _characterDetailsResponse.value = UIState.Error("Network error")
                    } else {
                        if (response.errorBody != null) {
                            Timber.d("Response is Not Null")
                            val error = response.errorBody.readError()
                            if (!error.isNullOrEmpty()) {
                                _characterDetailsResponse.value = UIState.Error(error)
                            } else {
                                _characterDetailsResponse.value =
                                    UIState.Error("Error fetching character details")
                            }

                        } else {
                            Timber.d("Response is Null")
                            _characterDetailsResponse.value =
                                UIState.Error("Error fetching character details")
                        }
                    }
                }
            }
        }
    }
}