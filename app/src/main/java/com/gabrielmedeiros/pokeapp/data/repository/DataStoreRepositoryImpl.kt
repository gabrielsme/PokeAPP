package com.gabrielmedeiros.pokeapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

private const val PREFERENCES_NAME = "my_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

private const val POKEMON_FAVORITE_KEY = "POKEMON_FAVORITE_KEY"

class DataStoreRepositoryImpl(
    private val context: Context
) : DataStoreRepository {

    override suspend fun saveFavorite(id: Int) {
        val preferencesKey = booleanPreferencesKey(getFavoritePokemonKey(id))
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = true
        }
    }

    override suspend fun removeFavorite(id: Int) {
        val preferencesKey = booleanPreferencesKey(getFavoritePokemonKey(id))
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = false
        }
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> {
        val preferencesKey = booleanPreferencesKey(getFavoritePokemonKey(id))
        val preferences = context.dataStore.data.first()
        return flow {
            val isFavorite = preferences[preferencesKey] ?: false
            emit(isFavorite)
        }
    }

    private fun getFavoritePokemonKey(id: Int) = "${POKEMON_FAVORITE_KEY}_$id"
}