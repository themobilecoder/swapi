package com.rafaelds.swapi.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class SpecieListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Specie>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecieListViewHolder(binding)
    }

    inner class SpecieListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Specie>(binding) {
        override fun setData(model: Specie, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.name
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
        }

    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Specie>() {
            override fun areItemsTheSame(oldItem: Specie, newItem: Specie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Specie, newItem: Specie): Boolean {
                return oldItem == newItem
            }
        }
    }
}