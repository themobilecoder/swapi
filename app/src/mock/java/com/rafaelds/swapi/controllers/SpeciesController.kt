package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class SpeciesController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/species/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.species)
            }
            url.matches("/api/species/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.specie)
            }
            else -> {
                null
            }
        }
    }

}