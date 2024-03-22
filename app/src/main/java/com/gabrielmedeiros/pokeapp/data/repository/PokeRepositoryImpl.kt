package com.gabrielmedeiros.pokeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.data.pagination.ListPokemonPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

const val NETWORK_PAGE_SIZE = 20

class PokeRepositoryImpl(
    private val listPokemonPagingSource: ListPokemonPagingSource,
    private val ioDispatcher: CoroutineDispatcher,
) : PokeRepository {

    override suspend fun listPokemon(): Flow<PagingData<ListPokemonModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                listPokemonPagingSource
            }
        ).flow.flowOn(ioDispatcher)
    }
}
