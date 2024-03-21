package com.gabrielmedeiros.pokeapp.data.repository

import com.gabrielmedeiros.pokeapp.data.model.ListPokemonResultsPage
import com.gabrielmedeiros.pokeapp.data.source.ApiRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokeRepositoryImpl(
    private val apiRemoteDataSource: ApiRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : PokeRepository {

    override suspend fun listPokemon(): Flow<ListPokemonResultsPage> =
        flow {
            emit(apiRemoteDataSource.listPokemon())
        }.flowOn(ioDispatcher)

}
