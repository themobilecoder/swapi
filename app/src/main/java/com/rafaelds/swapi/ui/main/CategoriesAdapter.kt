package com.rafaelds.swapi.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.rafaelds.swapi.R
import com.rafaelds.swapi.databinding.CardCategoryBinding

class CategoriesAdapter(context: Context, categories: Array<Category>) :
    ArrayAdapter<Category>(context, R.layout.card_category, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            CardCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            CardCategoryBinding.bind(convertView)
        }
        val item = getItem(position)
        binding.cardViewCategory.setOnClickListener { item?.onClick?.invoke() }
        binding.cardViewTitle.text = item?.title
        return binding.root
    }

}