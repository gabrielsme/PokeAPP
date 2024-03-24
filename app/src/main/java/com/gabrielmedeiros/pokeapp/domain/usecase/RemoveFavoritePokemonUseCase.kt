package com.gabrielmedeiros.pokeapp.domain.usecase

import com.gabrielmedeiros.pokeapp.data.repository.DataStoreRepository

class RemoveFavoritePokemonUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {

    suspend operator fun invoke(id: Int) = dataStoreRepository.removeFavorite(id)
}