package com.rafaelds.swapi.ui

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.ListItemLoadingBinding

class LoadAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadAdapter.PlanetLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PlanetLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PlanetLoadStateViewHolder {
        return PlanetLoadStateViewHolder.create(parent)
    }

    class PlanetLoadStateViewHolder(private val binding: ListItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): PlanetLoadStateViewHolder {
                val binding = ListItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlanetLoadStateViewHolder(binding)
            }
        }

        fun bind(loadState: LoadState, retry: () -> Unit) {
            if (loadState is LoadState.Loading) {
                binding.button.visibility = GONE
                binding.loadingSpinner.visibility = VISIBLE
            } else {
                binding.button.visibility = VISIBLE
                binding.loadingSpinner.visibility = GONE
            }
            binding.button.setOnClickListener { retry() }
        }

    }
}