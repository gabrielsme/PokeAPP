package com.gabrielmedeiros.pokeapp.data.source

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import retrofit2.http.GET

interface ApiRemoteDataSource {

    @GET("pokemon")
    suspend fun listPokemon(): ListPokemonResultsPage
}