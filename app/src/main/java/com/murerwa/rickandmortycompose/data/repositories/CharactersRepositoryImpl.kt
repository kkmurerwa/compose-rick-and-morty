package com.murerwa.rickandmortycompose.data.repositories

import com.murerwa.rickandmortycompose.data.network.ApiClient
import com.murerwa.rickandmortycompose.data.network.BaseRepository
import com.murerwa.rickandmortycompose.data.network.NetworkResult
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.domain.models.characters.CharactersResponse
import com.murerwa.rickandmortycompose.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(
    private val apiClient: ApiClient
): CharactersRepository, BaseRepository() {
    override suspend fun getCharacters(page: Int): NetworkResult<CharactersResponse> {
        return safeApiCall { apiClient.getCharacters(page) }
    }

    override suspend fun getCharacterDetails(characterId: Int): NetworkResult<CharacterItem> {
        return safeApiCall { apiClient.getCharacterDetails(characterId) }
    }

}