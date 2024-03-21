package com.gabrielmedeiros.pokeapp.domain.usecase

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    private val pokeRepository: PokeRepository
) {

    suspend operator fun invoke(): Flow<ListPokemonResultsPage> = pokeRepository.listPokemon()

}