package com.gabrielmedeiros.pokeapp.ui.main.detailpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabrielmedeiros.pokeapp.R
import com.gabrielmedeiros.pokeapp.databinding.FragmentDetailPokemonBinding

class DetailPokemonFragment : Fragment(R.layout.fragment_detail_pokemon) {

    private var binding: FragmentDetailPokemonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailPokemonBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailPokemonFragment()
    }
}