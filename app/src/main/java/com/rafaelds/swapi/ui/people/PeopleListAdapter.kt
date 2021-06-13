package com.rafaelds.swapi.ui.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.R

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

    class PeopleListViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        companion object {
            fun create(parent: ViewGroup): PeopleListViewHolder {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_people, parent, false)
                return PeopleListViewHolder(itemView)
            }
        }

        fun setData(people: People) {
            itemView.findViewById<TextView>(R.id.list_item_people_name).text = people.name
        }

    }
}