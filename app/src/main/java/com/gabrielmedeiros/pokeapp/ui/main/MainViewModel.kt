package com.gabrielmedeiros.pokeapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    fun listPokemon() {
        viewModelScope.launch {
            getPokemonListUseCase().collect {
                Log.d("GABRIEL", "Result: ${it.results.size}")
            }
        }
    }

}