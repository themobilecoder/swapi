package com.rafaelds.swapi.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.rafaelds.swapi.R

class CategoriesAdapter(context: Context, categories: Array<Category>) :
    ArrayAdapter<Category>(context, R.layout.card_category, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.card_category, parent, false)
            val titleView = view!!.findViewById<TextView>(R.id.card_view_title)
            val item = getItem(position)
            view.findViewById<CardView>(R.id.card_view_category)
                .setOnClickListener { item?.onClick?.invoke() }
            titleView.text = item?.title
        }
        return view
    }

}