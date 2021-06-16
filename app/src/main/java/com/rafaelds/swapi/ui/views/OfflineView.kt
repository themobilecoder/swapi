package com.rafaelds.swapi.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.rafaelds.swapi.R
import com.rafaelds.swapi.databinding.ViewOfflineBinding

class OfflineView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewOfflineBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.OfflineView, defStyleAttr, 0).apply {
            recycle()
        }
    }

}