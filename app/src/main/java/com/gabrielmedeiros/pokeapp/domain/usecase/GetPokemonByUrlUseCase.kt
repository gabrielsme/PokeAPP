package com.gabrielmedeiros.pokeapp.domain.usecase

import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository

class GetPokemonByUrlUseCase(
    private val pokeRepository: PokeRepository
) {

    suspend operator fun invoke(url: String) = pokeRepository.getPokemonByUrl(url)
}