package com.rafaelds.swapi.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.CardCategoryBinding

class CategoriesAdapter() :
    ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    inner class CategoriesViewHolder(private val binding: CardCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category): View {
            binding.cardViewCategory.setOnClickListener { category.onClick.invoke() }
            binding.cardViewTitle.text = category.title
            return binding.root
        }
    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }

}