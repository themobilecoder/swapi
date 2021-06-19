package com.rafaelds.swapi.ui

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.ListItemBinding

abstract class BaseListAdapter <T : Any> constructor( private val onClick: (url: String) -> Unit, diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseListAdapter.BaseListViewHolder<T>>(diffCallback) {

    override fun onBindViewHolder(holder: BaseListViewHolder<T>, position: Int) {
        val model = getItem(position)
        model?.let {
            holder.setData(model, onClick)
        }
    }

    abstract class BaseListViewHolder <T> constructor(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun setData(model: T, onClick: (String) -> Unit)
    }
}