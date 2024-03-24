package com.gabrielmedeiros.pokeapp.ui.main.detailpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.gabrielmedeiros.pokeapp.R
import com.gabrielmedeiros.pokeapp.databinding.FragmentDetailPokemonBinding
import com.gabrielmedeiros.pokeapp.ui.main.ListOnBackPressedCallback
import com.gabrielmedeiros.pokeapp.ui.main.MainActivity
import com.gabrielmedeiros.pokeapp.ui.main.MainViewModel
import com.gabrielmedeiros.pokeapp.util.extension.capitalize
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DetailPokemonFragment : Fragment(R.layout.fragment_detail_pokemon) {

    private var binding: FragmentDetailPokemonBinding? = null
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailPokemonBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slidingPaneLayout = (activity as MainActivity).getSlidingPane()
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            ListOnBackPressedCallback(slidingPaneLayout)
        )

        setupEvents()
        setupObservers()
    }

    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.pokemon?.let { pokemon ->
                        binding?.apply {
                            image.load(pokemon.getImageUrl())
                            name.text = pokemon.name.capitalize()
                            id.text = pokemon.id.toString()
                            height.text = pokemon.height.toString()
                            weight.text = pokemon.weight.toString()
                            baseExperience.text = pokemon.baseExperience.toString()
                            types.text = pokemon.getAllTypes()
                            abilities.text = pokemon.getAllAbilities()
                            favorite.isSelected = pokemon.isFavorite
                        }
                    }
                }
            }
        }
    }

    private fun setupEvents() {
        binding?.favorite?.setOnClickListener {
            binding?.favorite?.isSelected?.let { isSelected ->
                if (isSelected) {
                    viewModel.removeFavoritePokemon()
                } else {
                    viewModel.saveFavoritePokemon()
                }

                binding?.favorite?.isSelected = !isSelected
            }
        }
    }

}