package com.gabrielmedeiros.pokeapp.data.source

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import com.gabrielmedeiros.pokeapp.data.model.PokemonModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiRemoteDataSource {

    @GET("pokemon")
    suspend fun listPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): ListPokemonResultsPage

    @GET
    suspend fun getPokemonByUrl(@Url url: String): PokemonModel
}