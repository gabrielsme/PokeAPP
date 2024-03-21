package com.gabrielmedeiros.pokeapp.data.model

class ListPokemonResultsPage : BaseApiResponse<ListPokemonModel>()

data class ListPokemonModel(
    val name: String,
    val url: String
)