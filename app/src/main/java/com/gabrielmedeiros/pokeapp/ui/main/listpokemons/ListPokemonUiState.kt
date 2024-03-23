package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.data.model.PokemonModel

data class ListPokemonUiState(
    val isLoading: Boolean = false,
    val listPokemons: PagingData<ListPokemonModel>? = null,
    val pokemon: PokemonModel? = null
)