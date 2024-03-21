package com.gabrielmedeiros.pokeapp.ui.main

import androidx.lifecycle.ViewModel
import com.gabrielmedeiros.pokeapp.domain.usecase.GetPokemonListUseCase

class MainViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
}