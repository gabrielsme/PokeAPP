package com.gabrielmedeiros.pokeapp.data.repository

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun listPokemon(): Flow<ListPokemonResultsPage>
}