package com.rafaelds.swapi.ui.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class FilmListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Film>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmListViewHolder(binding)
    }

    inner class FilmListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Film>(binding) {
        override fun setData(model: Film, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.title
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
        }

    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }
}