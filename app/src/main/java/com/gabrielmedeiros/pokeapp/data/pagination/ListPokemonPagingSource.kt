package com.gabrielmedeiros.pokeapp.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.data.source.ApiRemoteDataSource

private const val QTY_ITEMS = 20

class ListPokemonPagingSource(
    private val apiRemoteDataSource: ApiRemoteDataSource,
) : PagingSource<Int, ListPokemonModel>() {
    override fun getRefreshKey(state: PagingState<Int, ListPokemonModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(QTY_ITEMS)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(QTY_ITEMS)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListPokemonModel> {
        return try {
            val offset = params.key ?: 0
            val response = apiRemoteDataSource.listPokemon(offset)

            LoadResult.Page(
                data = response.results,
                prevKey = if (offset == 0) null else (offset - QTY_ITEMS),
                nextKey = if (offset == response.count) null else (offset + QTY_ITEMS)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}