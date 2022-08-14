package com.murerwa.rickandmortycompose.domain.repositories

import com.murerwa.rickandmortycompose.data.network.NetworkResult
import com.murerwa.rickandmortycompose.domain.models.characters.CharacterItem
import com.murerwa.rickandmortycompose.domain.models.characters.CharactersResponse

interface CharactersRepository {

    suspend fun getCharacters(page: Int): NetworkResult<CharactersResponse>

    suspend fun getCharacterDetails(characterId: Int): NetworkResult<CharacterItem>

}