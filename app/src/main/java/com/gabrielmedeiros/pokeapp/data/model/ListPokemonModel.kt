package com.gabrielmedeiros.pokeapp.data.model

import com.gabrielmedeiros.pokeapp.util.getPokemonImageUrl

data class ListPokemonModel(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        val id = url.split("/").reversed()[1]
        return getPokemonImageUrl(id)
    }
}