package com.gabrielmedeiros.pokeapp.data.source

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRemoteDataSource {

    @GET("pokemon")
    suspend fun listPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): ListPokemonResultsPage
}