package com.rafaelds.swapi.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.databinding.ListItemBinding
import com.rafaelds.swapi.ui.BaseListAdapter

class VehicleListAdapter(onClick: (url: String) -> Unit) : BaseListAdapter<Vehicle>(onClick, ASYNC_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleListViewHolder(binding)
    }

    inner class VehicleListViewHolder constructor(private val binding: ListItemBinding) :
        BaseListAdapter.BaseListViewHolder<Vehicle>(binding) {
        override fun setData(model: Vehicle, onClick: (String) -> Unit) {
            binding.itemTitle.text = model.name
            binding.root.setOnClickListener {
                onClick(model.appUri)
            }
        }

    }

    companion object {
        private val ASYNC_DIFF = object : DiffUtil.ItemCallback<Vehicle>() {
            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem == newItem
            }
        }
    }
}