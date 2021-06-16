package com.rafaelds.swapi.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.databinding.ListItemBinding

class PersonListAdapter(private val onClick: (url: String) -> Unit) :
    PagingDataAdapter<Person, PersonListAdapter.PersonListViewHolder>(ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListViewHolder {
        return PersonListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PersonListViewHolder, position: Int) {
        val person = getItem(position)
        person?.let {
            holder.setData(person, onClick)
        }
    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Person>() {

            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }

        }
    }

    class PersonListViewHolder private constructor(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): PersonListViewHolder {
                val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PersonListViewHolder(binding)
            }
        }

        fun setData(person: Person, onClick: (String) -> Unit) {
            binding.itemTitle.text = person.name
            binding.root.setOnClickListener {
                onClick(person.appUri)
            }
        }

    }
}