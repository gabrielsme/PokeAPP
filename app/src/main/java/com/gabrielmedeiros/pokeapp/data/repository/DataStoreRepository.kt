package com.gabrielmedeiros.pokeapp.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveFavorite(id: Int)
    suspend fun removeFavorite(id: Int)
    suspend fun isFavorite(id: Int): Flow<Boolean>
}