package com.gabrielmedeiros.pokeapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gabrielmedeiros.pokeapp.data.model.PokemonModel
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonByUrlUseCase
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase
import com.gabrielmedeiros.pokeapp.domain.usecase.RemoveFavoritePokemonUseCase
import com.gabrielmedeiros.pokeapp.domain.usecase.SaveFavoritePokemonUseCase
import com.gabrielmedeiros.pokeapp.ui.main.listpokemons.ListPokemonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonByUrlUseCase: GetPokemonByUrlUseCase,
    private val saveFavoritePokemonUseCase: SaveFavoritePokemonUseCase,
    private val removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListPokemonUiState())
    val uiState: StateFlow<ListPokemonUiState> = _uiState.asStateFlow()

    fun listPokemon() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getPokemonListUseCase().cachedIn(viewModelScope).collectLatest { result ->
                _uiState.update { it.copy(listPokemons = result, isLoading = false) }
            }
        }
    }

    fun getPokemonByUrl(url: String) {
        viewModelScope.launch {
            getPokemonByUrlUseCase(url).collect { result ->
                _uiState.update { it.copy(pokemon = result) }
            }
        }
    }

    fun saveFavoritePokemon() {
        viewModelScope.launch {
            _uiState.first().pokemon?.id?.let { id ->
                saveFavoritePokemonUseCase(id)
            }
        }
    }

    fun removeFavoritePokemon() {
        viewModelScope.launch {
            _uiState.first().pokemon?.id?.let { id ->
                removeFavoritePokemonUseCase(id)
            }
        }
    }
}