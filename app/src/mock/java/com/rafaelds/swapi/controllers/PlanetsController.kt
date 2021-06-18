package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class PlanetsController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/planets/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.planets)
            }
            url.matches("/api/planets/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.planet)
            }
            else -> {
                null
            }
        }
    }

}