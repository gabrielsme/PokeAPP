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
import com.gabrielmedeiros.pokeapp.ui.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

private const val COLUMN_COUNT = 2

class ListPokemonFragment : Fragment(R.layout.adapter_list_pokemon) {

    private var binding: FragmentListPokemonBinding? = null
    private val viewModel by activityViewModel<MainViewModel>()

    private var adapter: ListPokemonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListPokemonBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListPokemonAdapter(::onClickPokemonItem)
        binding?.list?.layoutManager = GridLayoutManager(context, COLUMN_COUNT)
        binding?.list?.adapter = adapter

        setupObservers()

        viewModel.listPokemon()
    }

    override fun onDestroyView() {
        binding = null
        adapter = null

        super.onDestroyView()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.listPokemons?.let { pokemons ->
                        adapter?.submitData(pokemons)
                    }
                }
            }
        }
    }

    private fun onClickPokemonItem(pokemon: ListPokemonModel) {
        viewModel.getPokemonByUrl(pokemon.url)
        (activity as MainActivity).openPane()
    }

}