package com.rafaelds.swapi.ui.people

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.ListItemLoadingBinding

class PersonLoadAdapter(private val retry: () -> Unit) : LoadStateAdapter<PersonLoadAdapter.PersonLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PersonLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PersonLoadStateViewHolder {
        return PersonLoadStateViewHolder.create(parent)
    }

    class PersonLoadStateViewHolder(private val binding: ListItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object  {
            fun create(parent: ViewGroup) : PersonLoadStateViewHolder {
                val binding = ListItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PersonLoadStateViewHolder(binding)
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