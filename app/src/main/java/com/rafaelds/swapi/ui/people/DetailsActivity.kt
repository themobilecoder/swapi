package com.rafaelds.swapi.ui.people

import android.os.Bundle
import com.rafaelds.swapi.databinding.ActivityDetailsBinding
import com.rafaelds.swapi.ui.BaseActivity

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
    }
}