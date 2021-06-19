package com.rafaelds.swapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.R
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.databinding.ViewTitleDescBinding

class LinksAdapter(private val onClick: (url: String) -> Unit) : ListAdapter<LinksData, LinksAdapter.LinksViewHolder>(ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        val binding = ViewTitleDescBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class LinksViewHolder(private val binding: ViewTitleDescBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: LinksData, onClick: (url: String) -> Unit) {
            binding.title.text = model.name
            binding.description.text = binding.root.context.getString(R.string.parenthesis, model.description)
            binding.root.setOnClickListener {
                onClick(model.url)
            }
        }
    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<LinksData>() {
            override fun areItemsTheSame(oldItem: LinksData, newItem: LinksData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LinksData, newItem: LinksData): Boolean {
                return oldItem == newItem
            }
        }
    }

}