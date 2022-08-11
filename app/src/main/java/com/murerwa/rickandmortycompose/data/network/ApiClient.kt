package com.murerwa.rickandmortycompose.data.network

import com.murerwa.rickandmortycompose.domain.models.characters.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(Urls.CHARACTERS)
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharactersResponse
}