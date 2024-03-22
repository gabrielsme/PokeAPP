package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.databinding.AdapterListPokemonBinding
import com.gabrielmedeiros.pokeapp.util.extension.capitalize

class ListPokemonAdapter(
    private val values: List<ListPokemonModel>,
    private val onClickEvent: (ListPokemonModel) -> Unit,
) : RecyclerView.Adapter<ListPokemonAdapter.ListPokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        return ListPokemonViewHolder(
            AdapterListPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ListPokemonViewHolder(
        private val binding: AdapterListPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListPokemonModel) {
            binding.image.load(item.getImageUrl())
            binding.name.text = item.name.capitalize()
            binding.root.setOnClickListener { onClickEvent(item) }
        }
    }
}