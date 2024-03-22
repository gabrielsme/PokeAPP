package com.gabrielmedeiros.pokeapp.data.repository

import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun listPokemon(): Flow<PagingData<ListPokemonModel>>
}