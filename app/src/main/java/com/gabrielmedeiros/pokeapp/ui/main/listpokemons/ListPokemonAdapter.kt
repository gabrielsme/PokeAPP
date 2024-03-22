package com.gabrielmedeiros.pokeapp.ui.main.listpokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabrielmedeiros.pokeapp.data.model.ListPokemonModel
import com.gabrielmedeiros.pokeapp.databinding.AdapterListPokemonBinding
import com.gabrielmedeiros.pokeapp.util.extension.capitalize

class ListPokemonAdapter(
    private val onClickEvent: (ListPokemonModel) -> Unit,
) : PagingDataAdapter<ListPokemonModel, ListPokemonAdapter.ListPokemonViewHolder>(
    ListPokemonDiffCallBack()
) {

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
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    class ListPokemonDiffCallBack : DiffUtil.ItemCallback<ListPokemonModel>() {
        override fun areItemsTheSame(
            oldItem: ListPokemonModel,
            newItem: ListPokemonModel,
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ListPokemonModel,
            newItem: ListPokemonModel,
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ListPokemonViewHolder(
        private val binding: AdapterListPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListPokemonModel) {
            binding.image.load(item.getImageUrl())
            binding.name.text = item.name.capitalize()
            binding.root.setOnClickListener { onClickEvent(item) }
        }
    }
}