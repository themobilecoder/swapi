package com.rafaelds.swapi.ui.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class StarshipListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Starship>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StarshipListViewHolder(binding)
    }

    inner class StarshipListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Starship>(binding) {
        override fun setData(model: Starship, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.name
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
        }

    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Starship>() {
            override fun areItemsTheSame(oldItem: Starship, newItem: Starship): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Starship, newItem: Starship): Boolean {
                return oldItem == newItem
            }
        }
    }
}