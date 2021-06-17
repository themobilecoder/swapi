package com.rafaelds.swapi.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class PersonListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Person>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonListViewHolder(binding)
    }

    inner class PersonListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Person>(binding) {
        override fun setData(model: Person, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.name
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
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
}