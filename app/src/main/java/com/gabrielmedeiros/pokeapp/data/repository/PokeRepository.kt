package com.gabrielmedeiros.pokeapp.data.repository

import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.data.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun listPokemon(): Flow<PagingData<ListPokemonModel>>
    suspend fun getPokemonByUrl(url: String): Flow<PokemonModel>
}