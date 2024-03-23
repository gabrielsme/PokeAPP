package com.gabrielmedeiros.pokeapp.data.model

import com.gabrielmedeiros.pokeapp.util.getPokemonImageUrl
import com.google.gson.annotations.SerializedName

data class PokemonModel(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerializedName("base_experience") val baseExperience: Int,
    val abilities: List<Ability>,
    val types: List<Type>
) {
    fun getImageUrl() = getPokemonImageUrl(id.toString())

    fun getAllTypes() =  types.joinToString(", ") { it.type.name }

    fun getAllAbilities() = abilities.joinToString(", ") { it.ability.name }
}

data class Ability(
    val ability: AbilityDetail,
)

data class AbilityDetail(
    val name: String,
    val url: String
)

data class Type(
    val type: TypeDetail
)

data class TypeDetail(
    val name: String
)