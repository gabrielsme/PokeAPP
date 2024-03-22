package com.gabrielmedeiros.pokeapp.domain.usecase

import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    private val pokeRepository: PokeRepository
) {

    suspend operator fun invoke(): Flow<PagingData<ListPokemonModel>> = pokeRepository.listPokemon()

}