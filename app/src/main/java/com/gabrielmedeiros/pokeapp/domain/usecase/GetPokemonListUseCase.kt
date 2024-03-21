package com.gabrielmedeiros.pokeapp.domain.usecase

import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository

class GetPokemonListUseCase(
    private val pokeRepository: PokeRepository
) {
}