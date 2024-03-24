package com.gabrielmedeiros.pokeapp.domain.usecase

import com.gabrielmedeiros.pokeapp.data.model.PokemonModel
import com.gabrielmedeiros.pokeapp.data.repository.DataStoreRepository
import com.gabrielmedeiros.pokeapp.data.repository.PokeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class GetPokemonByUrlUseCase(
    private val pokeRepository: PokeRepository,
    private val dataStoreRepository: DataStoreRepository,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(url: String): Flow<PokemonModel> {
        return pokeRepository.getPokemonByUrl(url).flatMapMerge { pokemon ->
            dataStoreRepository.isFavorite(pokemon.id).map { isFavorite ->
                pokemon.isFavorite = isFavorite
                pokemon
            }
        }
    }
}