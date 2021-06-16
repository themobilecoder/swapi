package com.rafaelds.swapi.ui.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.databinding.ListItemBinding

class PlanetListAdapter(private val onClick: (url: String) -> Unit) :
    PagingDataAdapter<Planet, PlanetListAdapter.PlanetListViewHolder>(ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetListViewHolder {
        return PlanetListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlanetListViewHolder, position: Int) {
        val planet = getItem(position)
        planet?.let {
            holder.setData(planet, onClick)
        }
    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Planet>() {

            override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem == newItem
            }

        }
    }

    class PlanetListViewHolder private constructor(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): PlanetListViewHolder {
                val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlanetListViewHolder(binding)
            }
        }

        fun setData(person: Planet, onClick: (String) -> Unit) {
            binding.itemTitle.text = person.name
            binding.root.setOnClickListener {
                onClick(person.appUri)
            }
        }

    }
}