package com.murerwa.rickandmortycompose.domain.models.characters

data class CharactersResponse(
    val info: Info,
    val results: List<CharacterItem>
)