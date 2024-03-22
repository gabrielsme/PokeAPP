package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielmedeiros.pokeapp.R
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.databinding.FragmentListPokemonBinding
import com.gabrielmedeiros.pokeapp.ui.main.MainActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val COLUMN_COUNT = 2

class ListPokemonFragment : Fragment(R.layout.adapter_list_pokemon) {

    private var binding: FragmentListPokemonBinding? = null
    private val viewModel by viewModel<ListPokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListPokemonBinding.inflate(layoutInflater, container, false)

        binding?.list?.layoutManager = GridLayoutManager(context, COLUMN_COUNT)

        setupObservers()

        viewModel.listPokemon()

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding?.list?.adapter = ListPokemonAdapter(uiState.pokemons, ::onClickPokemonItem)
                }
            }
        }
    }

    private fun onClickPokemonItem(pokemon: ListPokemonModel) {
        (activity as MainActivity).openPane()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListPokemonFragment()
    }
}