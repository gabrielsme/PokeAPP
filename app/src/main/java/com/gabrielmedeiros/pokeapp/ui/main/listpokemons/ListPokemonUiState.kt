package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel

data class ListPokemonUiState(
    val isLoading: Boolean = false,
    val pokemons: PagingData<ListPokemonModel>? = null
)