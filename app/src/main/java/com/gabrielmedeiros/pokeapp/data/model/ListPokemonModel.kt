package com.gabrielmedeiros.pokeapp.data.model

data class ListPokemonModel(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        val id = url.split("/").reversed()[1]
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}