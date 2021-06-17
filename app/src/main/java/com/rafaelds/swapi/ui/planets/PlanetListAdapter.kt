package com.rafaelds.swapi.ui.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class PlanetListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Planet>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetListViewHolder(binding)
    }

    inner class PlanetListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Planet>(binding) {
        override fun setData(model: Planet, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.name
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
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
}