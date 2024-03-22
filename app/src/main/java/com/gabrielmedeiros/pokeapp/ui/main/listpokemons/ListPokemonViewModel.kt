package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListPokemonUiState())
    val uiState: StateFlow<ListPokemonUiState> = _uiState.asStateFlow()

    fun listPokemon() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getPokemonListUseCase().collectLatest { result ->
                _uiState.update { it.copy(pokemons = result, isLoading = false) }
            }
        }
    }

}