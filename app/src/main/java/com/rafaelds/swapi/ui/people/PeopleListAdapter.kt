package com.rafaelds.swapi.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.ListItemPeopleBinding

class PeopleListAdapter : ListAdapter<People, PeopleListAdapter.PeopleListViewHolder>(ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        return PeopleListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<People>() {

            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem == newItem
            }

        }
    }

    class PeopleListViewHolder private constructor(private val binding: ListItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): PeopleListViewHolder {
                val binding = ListItemPeopleBinding.inflate(LayoutInflater.from(parent.context))
                return PeopleListViewHolder(binding)
            }
        }

        fun setData(people: People) {
            binding.itemTitle.text = people.name
        }

    }
}