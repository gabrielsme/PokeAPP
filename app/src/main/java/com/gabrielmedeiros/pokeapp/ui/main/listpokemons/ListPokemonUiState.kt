package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel

data class ListPokemonUiState(
    val isLoading: Boolean = false,
    val pokemons: List<ListPokemonModel> = listOf()
)