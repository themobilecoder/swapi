package com.rafaelds.swapi.ui

import android.os.Bundle
import com.rafaelds.swapi.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarDetails
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}